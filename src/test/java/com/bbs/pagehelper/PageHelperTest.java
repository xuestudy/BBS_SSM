package com.bbs.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xue.bbs.dao.ArticleMapper;
import com.xue.bbs.pojo.Article;

public class PageHelperTest {
	
	@SuppressWarnings("resource")
	@Test
	public void test() {
		//初始化spring容器
		ApplicationContext applicationContext = new 
				ClassPathXmlApplicationContext("classpath:spring/ApplicationContext.xml");
		
		//得到一个查询的bean
		ArticleMapper articleMapper = applicationContext.getBean(ArticleMapper.class);
		
		//设置分页信息 当前页1 一页记录数3条
		PageHelper.startPage(1, 3);
		
		//执行mysql查询
		List<Article> list = articleMapper.selectAll(0);
		
		//得到分页信息
		PageInfo<Article> pageInfo = new PageInfo<>(list);
		System.out.println(pageInfo.getTotal());//获得总记录数
		System.out.println(pageInfo.getPageNum());//获得当前页数
		System.out.println(pageInfo.getSize());//获得一页有多少条记录
		System.out.println(pageInfo.getPages());//获得多少页
		
	}

}
