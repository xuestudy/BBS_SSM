package com.xue.bbs.service;

import java.util.HashMap;
import java.util.List;

import com.github.pagehelper.Page;
import com.xue.bbs.pojo.Article;

/**
 * 页面处理逻辑
 * @author xuexue
 *
 */
public interface PageService {
	
	/**
	 * 功能：查询父贴数据，进行页面分页逻辑处理 
	 * @return 返回一个hashmap 保存page 与 List<Article>的信息
	 */
	public HashMap<List<Article>, Page<Article>> getShowData(Integer pageNum);
	
	/**
	 * 功能：根据搜索内容进行模糊查询，进行页面分页逻辑处理 
	 * @return 返回一个hashmap 保存page 与 List<Article>的信息
	 */
	public HashMap<List<Article>, Page<Article>> getSearchData(String title, Integer pageNum);

}
