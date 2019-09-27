package com.xue.bbs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 1、退出操作，注销session
 * @author xuexue
 *
 */
@Controller
public class OtherController {
	
	/**
	 * 退出登录，注销session
	 * @param session session获取
	 * @return
	 */
	@RequestMapping("/exit")
	public String exit(HttpSession session) {
		//获取登录时设置的 session
		String loginStatus = (String) session.getAttribute("loginStatus");
		if (loginStatus.equals("success")) {//判断内容是否是登录时设置的值
			//是 注销session
			session.invalidate();
			return "exit";
		}
		
		//否则，重定向主页
		return "redirect:/article/flat";
	}
}
