package com.xue.bbs.service;

import com.xue.bbs.pojo.BUser;

public interface LoginService {
	/**
	 * 登录检查
	 * @param password 密码
	 * @param username 用户名
	 * @return 
	 */
	BUser loginCheck(BUser user);

}
