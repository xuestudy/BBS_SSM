package com.xue.bbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xue.bbs.pojo.Article;

public interface ArticleMapper {

	/**
	 * 查询所有父帖
	 * @param pid 父帖参数
	 * @return
	 */
	List<Article> selectAll(@Param("pid") Integer pid);

	/**
	 * 根据搜索条件进行模糊查询
	 * @param title 搜索条件
	 * @return
	 */
	List<Article> selectByTitle(@Param("title") String title);
	
	/**
	 * 发布文章
	 */
	void postArticle(Article article);

	/**
	 * 删除指定id帖子和id的子帖
	 * @param id 帖子id
	 */
	void deleteArticleById(@Param("id") Integer id);

	/**
	 * 更新帖子
	 * @param article
	 */
	void updateArticle(Article article);

	/**
	 * 根据父帖id,查询所有的有关系的帖子
	 * @param rootid
	 * @return
	 */
	List<Article> selectAllByRootid(@Param("rootid") Integer rootid);

	/**
	 * 根据id查询出整个帖子信息
	 * @param id 帖子id
	 * @return
	 */
	Article selectById(@Param("id") Integer id);
	
	/**
	 * 回复帖子
	 * @param article
	 */
	void replyArticle(Article article);

	/**
	 * 将id帖子的leaf改为1
	 * @param id
	 */
	void updateArticleLeafById(@Param("id") Integer id);
	

}
