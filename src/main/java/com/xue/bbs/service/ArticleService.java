package com.xue.bbs.service;

import java.util.List;

import com.xue.bbs.pojo.Article;

/**
 * 接口：帖子操作功能------增删改查
 * @author xuexue
 *
 */
public interface ArticleService {
	/**
	 * 功能：查询主题帖子
	 * @param pid pid = 0，就是所有父帖子的条件
	 * @return 主题帖子主题
	 */
	List<Article> queryArticleAll(int pid);
	
	/**
	 * 功能：查询一个父id下的所有有关系的帖子
	 * @param rootid rootid = id，就是父帖子的id
	 * @return 一个帖子的子集合
	 */
	List<Article> queryArticleAllByRootid(int rootid);
	
	/**
	 * 功能：根据id查询单个
	 * @param id 帖子唯一标识
	 * @return 帖子
	 */
	Article queryArticleById(int id);
	
	/**
	 * 功能：根据title模糊查询帖子
	 * @param title 模糊查询条件
	 * @return 符合条件的帖子
	 */
	List<Article> searchArticleByTitle(String title);
	
	/**
	 * 功能：发布帖子
	 * @param article 封装了新帖的详细信息
	 * 
	 */
	void addArticle(Article article);
	
	/**
	 * 功能：根据id删除帖子
	 * @param id 帖子唯一标识
	 * 
	 */
	void deleteArticleById(int id);
	
	/**
	 * 功能：更新帖子
	 * @param article 封装了更新的帖子的信息
	 * 
	 */
	void updateArticleById(Article article);
	
	/**
	 * 功能：回复帖子
	 * @param article
	 */
	void replyArticle(Article article);
	
}
