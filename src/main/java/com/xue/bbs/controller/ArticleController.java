package com.xue.bbs.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.xue.bbs.pojo.Article;
import com.xue.bbs.service.ArticleService;
import com.xue.bbs.service.PageService;

/**
 * 帖子的增删改查
 * @author xuexue
 *
 */
@Controller
public class ArticleController {
	
	//注入service类,操作帖子
	@Autowired
	private PageService pageService;
	
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 展示主页、分页
	 * @return 主页
	 */
	@RequestMapping("/article/flat")
	public String showIndex(Model model, @RequestParam(defaultValue="1") Integer pageNum) {
		//查出所有主题帖 分页
		HashMap<List<Article>, Page<Article>> hashMap = pageService.getShowData(pageNum);
		for (List<Article> key : hashMap.keySet()) {
			//设置信息
			model.addAttribute("list", key);
			model.addAttribute("page", hashMap.get(key));
		}
		
		//跳转首页
		return "articleFlat";
	}
	
	/**
	 * 搜索功能 模糊搜索
	 * @return 主页
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/article/searchresult")
	public String articleSearch(Model model, @RequestParam(required=true, defaultValue=" ") String title, @RequestParam(defaultValue="1") Integer pageNum) throws UnsupportedEncodingException {
		//查询出帖子 分页查询
		HashMap<List<Article>, Page<Article>> hashMap = pageService.getSearchData(title, pageNum);
		for (List<Article> key : hashMap.keySet()) {
			//设置信息
			model.addAttribute("list", key);
			model.addAttribute("page", hashMap.get(key));
		}
		//设置信息
		model.addAttribute("title", title);
		
		//跳转搜索页面
		return "searchResult";
	}
	
	/**
	 * 发布新主题
	 * @return
	 */
	@RequestMapping(value = "/postedit", method = RequestMethod.POST)
	public String postArticle(Article article) {
		articleService.addArticle(article);
		return "postDeal";
	}
	
	/**
	 * 删除指定id帖子和id帖子的子帖
	 * @param id帖子id
	 * @return
	 */
	@RequestMapping(value = "/deleteFlat")
	public String deleteArticle(Integer id) {
		//删除帖子
		articleService.deleteArticleById(id);
		
		//重定向返回主页面
		return "redirect:/article/flat";
	}
	
	/**
	 * 更新帖子
	 * @param id帖子id
	 * @return
	 */
	@RequestMapping(value = "/modifyFlat", method = RequestMethod.POST)
	public String modifyArticle(Article article) {
		//更新帖子
		articleService.updateArticleById(article);
		
		//重定向返回主页面
		return "redirect:/article/flat";
	}
	
	/**
	 * 查询对应帖子id详细
	 * @param id帖子id
	 * @return
	 */
	@RequestMapping(value = "/articleDetailFlat", method = RequestMethod.GET)
	public String articleDetailFlat(Integer id, Model model) {
		//根据id查出整个主题帖信息
		Article article = articleService.queryArticleById(id);
		
		//根据id查出id的子级帖子 级关联查询
		List<Article> list = articleService.queryArticleAllByRootid(id);
		
		//将父帖添加到list中
		List<Article> list2 = new ArrayList<Article>();
		list2.add(article);
		list2.addAll(list);
		
		//设置域信息
		model.addAttribute("title", article.getTitle());
		model.addAttribute("id", article.getId());
		model.addAttribute("list", list2);
		
		//跳转帖子详细页面
		return "articleDetailFlat";
	}
	
	/**
	 * 回复主题
	 * @return
	 */
	@RequestMapping(value = "/replyDealFlat", method = RequestMethod.POST)
	public String replyDealArticle(Article article) {
		articleService.replyArticle(article);
		return "replyDealFlat";
	}
	
	
	
}
