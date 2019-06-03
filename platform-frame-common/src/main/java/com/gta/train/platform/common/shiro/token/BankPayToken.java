package com.gta.train.platform.common.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

public class BankPayToken extends UsernamePasswordRoleToken {

	public BankPayToken(String username, String password, String userSide, String platformUserId,String client) {
		super(username, password, "");
		this.userSide = userSide;
		this.platformUserId = platformUserId;
		this.client=client;
	}

	public BankPayToken(String username, String password, String role, boolean rememberMe, String host) {
		super(username, password != null ? password.toCharArray() : null, role, rememberMe, host);
	}

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String userSide;// 0、个人用户 1、企业操作员 2、企业管理员 3、银行管理员
	private String client;// 1、个人用户 2、企业 3、管理员
	private String platformUserId;

	public String getPlatformUserId() {
		return platformUserId;
	}

	public void setPlatformUserId(String platformUserId) {
		this.platformUserId = platformUserId;
	}

	public String getUserSide() {
		return userSide;
	}

	public void setUserSide(String userSide) {
		this.userSide = userSide;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

}
