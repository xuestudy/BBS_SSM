package com.xue.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xue.bbs.pojo.Article;

/**
 * 功能：控制器---负责页面跳转
 * 1、跳转到登录页面
 * 2、跳转到发布帖子页面
 * 3、跳转到修改帖子页面
 * 4、重定向到搜索controller 防止表单重复提交
 * 5、跳转到回复主题的页面
 * @author xuexue
 *
 */

@Controller
public class PageController {
	
	/**
	 * 跳转到登录页面
	 * @return
	 */
	@RequestMapping("/user/login")
	public String showLogin() {
		return "login";
	}
	
	/**
	 * 跳转到发布帖子页面
	 * @return
	 */
	@RequestMapping("/postedit")
	public String post() {
		return "post";
	}
	
	/**
	 * 跳转到修改帖子页面
	 * @return
	 */
	@RequestMapping("/modifyFlat")
	public String modifyFlat(Article article, Model model) {
		model.addAttribute("article", article);
		return "modifyFlat";
	}
	
	/**
	 * 重定向到搜索controller 防止表单重复提交
	 * @return
	 */
	@RequestMapping("/article/search")
	public String toSearch(String title) {
		String url = "redirect:/article/searchresult?title=" + title;
		return url;
	}
	
	/**
	 * 跳转到回复主题的页面
	 * @return
	 */
	@RequestMapping(value = "/replyFlat", method = RequestMethod.GET)
	public String replyArticle(Article article, Model model) {
		System.out.println(article.toString());
		model.addAttribute("id", article.getId());
		model.addAttribute("rootid", article.getRootid());
		return "replyFlat";
	}
	
}
