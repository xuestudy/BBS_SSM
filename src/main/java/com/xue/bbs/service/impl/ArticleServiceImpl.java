package com.xue.bbs.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xue.bbs.common.JedisClient;
import com.xue.bbs.common.JsonUtils;
import com.xue.bbs.dao.ArticleMapper;
import com.xue.bbs.pojo.Article;
import com.xue.bbs.service.ArticleService;

/**
 * 帖子 业务处理层
 * @author xuexue
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	//注入一个ArticleMapper
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${index_article}")
	private String key;
	
	public List<Article> queryArticleAll(int pid) {
		//查询到父帖集合并返回
		return articleMapper.selectAll(pid);
		
	}

	public Article queryArticleById(int id) {
		//根据id查询帖子
		return articleMapper.selectById(id);
	}

	public List<Article> searchArticleByTitle(String title) {
		//模糊查询
		return articleMapper.selectByTitle(title);
	}

	public void addArticle(Article article) {
		//发布帖子，新帖pid=0 rootid=0
		article.setPid(0);
		article.setRootid(0);
		articleMapper.postArticle(article);

	}

	public void deleteArticleById(int id) {
		//删除帖子
		articleMapper.deleteArticleById(id);
		
		//缓存同步，删除帖子也要删除缓存
		String cont = jedisClient.hget(key, id + "");
		if(StringUtils.isNotBlank(cont)) {//若缓存存在则删除
			try {
				jedisClient.hdel(key, id + "");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public void updateArticleById(Article article) {
		//更新帖子
		articleMapper.updateArticle(article);

	}

	@Override
	public List<Article> queryArticleAllByRootid(int rootid) {
		//查询缓存
		String cont = jedisClient.hget(key, rootid + "");
		try {//缓存存在，从缓存中获取信息
			if(StringUtils.isNotBlank(cont)) {
				List<Article> list = JsonUtils.jsonToList(cont, Article.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//缓存不存在，查询数据库
		//查询属于同一个rootid的帖子集合并返回
		List<Article> list = articleMapper.selectAllByRootid(rootid);
		
		try {
			//信息入缓存
			jedisClient.hset(key, rootid + "", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public void replyArticle(Article article) {
		//回复帖子
		int rootid = article.getRootid();
		//rootid=0就是回复的是主题帖，此时回复的帖子rootid=id;否则回复的是非主题帖此时回复的帖子rootid=rootid，也就不用做操作
		if(rootid == 0)
			article.setRootid(article.getId());
		//回复
		articleMapper.replyArticle(article);
		//将id的叶子改成1
		articleMapper.updateArticleLeafById(article.getId());
		
		//缓存同步，回复帖子也要删除缓存
		String cont = jedisClient.hget(key, article.getRootid() + "");
		if(StringUtils.isNotBlank(cont)) {//若缓存存在则删除
			try {
				jedisClient.hdel(key, article.getRootid() + "");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
