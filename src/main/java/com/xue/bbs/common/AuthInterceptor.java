package com.xue.bbs.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthInterceptor implements HandlerInterceptor {

	/* 
	 * 登录拦截，权限校验
	 * 拿到session，判断
	 * @return true不拦截，false拦截
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取session  ------ loginStatus
		Object loginStatus = request.getSession().getAttribute("loginStatus");
		
		//loginStatus为null说明没有设置session,拦截
		if (loginStatus == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			//request.getRequestDispatcher("/user/login").forward(request, response);
			return false;
		}
		
		//loginStatus 不为空，比较其值是否为 success 是不拦截（放行true） 否则拦截（不放行false）
		return loginStatus.toString().equals("success")  ? true : false;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
