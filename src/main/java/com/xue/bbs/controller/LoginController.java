package com.xue.bbs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xue.bbs.pojo.BUser;
import com.xue.bbs.service.LoginService;

/**
 * 功能：控制器----登录处理页面
 * @author xuexue
 *
 */
@RequestMapping("/user")
@Controller
public class LoginController {
	
	//注入service类，登录检查
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/loginCheck")
	public String loginCheck(BUser user, HttpSession session) {
		//判断登陆信息
		if (user != null && user.getUsername() != "" && user.getPassword() != "") {
			//验证登录信息是否正确
			BUser user2 = loginService.loginCheck(user);
			
			//登录成功，设置session，并跳转到主页
			if (user2 != null) {
				session.setAttribute("loginStatus", "success");
				//return "redirect:/article/all";
				return "redirect:/article/flat";
			}
		}
		//登录失败，重新进入登录页面
		return "loginFail";
	}
	
}
