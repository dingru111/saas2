package com.gta.train.platform.common.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 包含用户名、密码、角色的令牌类
 * 
 * @author ran.li
 * 
 */
public class UsernamePasswordRoleToken extends UsernamePasswordToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户类型
	 */
	private String userType;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public UsernamePasswordRoleToken(String username, String password,
			String role) {
		this(username, password != null ? password.toCharArray() : null, role,
				false, null);
	}

	public UsernamePasswordRoleToken(String username, String password,
			String role, boolean rememberMe) {
		this(username, password != null ? password.toCharArray() : null, role,
				rememberMe, null);
	}

	public UsernamePasswordRoleToken(String username, String password,
			String role, boolean rememberMe, String host) {
		this(username, password != null ? password.toCharArray() : null, role,
				rememberMe, host);
	}

	public UsernamePasswordRoleToken(String username, char[] password,
			String role, boolean rememberMe, String host) {
		super(username, password, rememberMe, host);
		this.userType = userType;
	}

	@Override
	public void clear() {
		super.clear();
		this.userType = null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getName());
		sb.append(" - ");
		sb.append(getUsername());
		sb.append(", rememberMe=").append(this.isRememberMe());
		if (userType != null) {
			sb.append(",role=").append(this.userType);
		}
		if (this.getHost() != null) {
			sb.append(" (").append(this.getHost()).append(")");
		}
		return sb.toString();
	}
}
