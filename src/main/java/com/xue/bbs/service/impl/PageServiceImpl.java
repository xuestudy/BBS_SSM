package com.xue.bbs.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xue.bbs.pojo.Article;
import com.xue.bbs.service.ArticleService;
import com.xue.bbs.service.PageService;

@Service
public class PageServiceImpl implements PageService {

	//注入分页信息页面大小
	@Value("${page.pageSize}")
	public String pageSize;

	//注入查询父帖信息 pid=0
	@Value("${article.pid}")
	public String pid;

	//注入service类,操作帖子
	@Autowired
	private ArticleService articleService;

	/* 
	 * 返回所有父帖子 分页
	 * (non-Javadoc)
	 * @see com.xue.bbs.service.PageService#getShowData(java.lang.Integer)
	 */
	public HashMap<List<Article>, Page<Article>> getShowData(Integer pageNum) {
		//初始化一个HashMap，存放信息
		HashMap<List<Article>, Page<Article>> hashMap = new HashMap<>();
		int pageSizee = Integer.parseInt(pageSize);
		int pidd = Integer.parseInt(pid);

		//设置分页信息，对应的是查询所有父帖pid=0
		Page<Article> page = PageHelper.startPage(pageNum, pageSizee);
		List<Article> list = articleService.queryArticleAll(pidd);

		//存放信息返回
		hashMap.put(list, page);
		return hashMap;
	}


	/* 模糊搜索 根据title
	 * (non-Javadoc)
	 * @see com.xue.bbs.service.PageService#getSearchData(java.lang.String, java.lang.Integer)
	 */
	@Override
	public HashMap<List<Article>, Page<Article>> getSearchData(String title, Integer pageNum) {
		int pageSizee = Integer.parseInt(pageSize);
		//初始化一个HashMap，存放信息
		HashMap<List<Article>, Page<Article>> hashMap = new HashMap<>();
		
		
		//对应下面语句的第一个mybatis查询
		Page<Article> page = PageHelper.startPage(pageNum, pageSizee);
		//根据搜索内容进行模糊查询
		List<Article> list = articleService.searchArticleByTitle(title);
		
		//存放信息返回
		hashMap.put(list, page);
		return hashMap;
	}

}