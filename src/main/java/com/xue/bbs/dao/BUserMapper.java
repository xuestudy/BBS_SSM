package com.xue.bbs.dao;

import com.xue.bbs.pojo.BUser;

public interface BUserMapper {
	/**
	 * 登录验证
	 * @param password 
	 * @param username 
	 * @return 1验证成功，0验证失败
	 */
	//int loginCheck(@Param("username") String username, @Param("password") String password);
	BUser loginCheck(BUser user);

}
