package com.xue.bbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xue.bbs.dao.BUserMapper;
import com.xue.bbs.pojo.BUser;
import com.xue.bbs.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private BUserMapper bUserMapper;

	public BUser loginCheck(BUser user) {
		return bUserMapper.loginCheck(user);
	}

}
