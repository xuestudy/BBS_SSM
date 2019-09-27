package com.xue.bbs.pojo;

/**
 * 用户类
 * @author xuexue
 *
 */
public class BUser {
	/**
	 * 用户id
	 */
	private int id;
	
	/**
	 * 登录名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "BUser [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
}
