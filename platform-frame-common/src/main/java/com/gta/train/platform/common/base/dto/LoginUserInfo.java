package com.gta.train.platform.common.base.dto;

import java.io.Serializable;
import java.util.Date;

import com.gta.train.platform.persis.mybatis.plugin.baseopt.interfaces.UserInfo;

public class LoginUserInfo  implements Serializable,UserInfo {

	private static final long serialVersionUID = 6933255704085410715L;
	private String userId; // t_login_info id
	private String platformUserId;
	private String personUserId; // t_person_info id
	private String companyUserId;
	private String bankUserId;// t_login_info id
	private String username;
    private Integer isComplete; //add by xiali
    private String operatorPermissions;
    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }

    private String companyId;
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String password;
	private String userSide;// 0、个人用户 1、企业操作员 2、企业管理员 3、银行管理员   9平台管理员 8平台教师管理员
	private String client;// 1、个人用户 2、企业 3、管理员   9 超管 
	/**huan.xu add  后期移走，单独弄一个Dto*/
	/**姓名*/
	private  String name;
	/**登录次数*/
	private Integer loginCount;
	/**本次登录时间*/
	private Date loginTime;
	/**本次登录ip*/
	private String loginIp;
	/**上次登录时间*/
	private Date loginLastTime;
	/**上次登录ip*/
	private String loginLastIp;
	/**身份证*/
	private String idNum;
	/**phone*/
	private String phone;
	/**风险评级*/
	private Integer riskRating;
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPlatformUserId() {
		return platformUserId;
	}

	public void setPlatformUserId(String platformUserId) {
		this.platformUserId = platformUserId;
	}

	public String getPersonUserId() {
		return personUserId;
	}

	public void setPersonUserId(String personUserId) {
		this.personUserId = personUserId;
	}

	public String getCompanyUserId() {
		return companyUserId;
	}

	public void setCompanyUserId(String companyUserId) {
		this.companyUserId = companyUserId;
	}

	public String getBankUserId() {
		return bankUserId;
	}

	public void setBankUserId(String bankUserId) {
		this.bankUserId = bankUserId;
	}

 

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
 

	@Override
	public String toString() {
		return "LoginUserInfo [userId=" + userId + ", platformUserId=" + platformUserId + ", personUserId="
				+ personUserId + ", companyUserId=" + companyUserId + ", bankUserId=" + bankUserId + ", password="
				+ password + ", userSide=" + userSide + ", client=" + client + "]";
	}
 
 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginLastTime() {
		return loginLastTime;
	}

	public void setLoginLastTime(Date loginLastTime) {
		this.loginLastTime = loginLastTime;
	}

	public String getLoginLastIp() {
		return loginLastIp;
	}

	public void setLoginLastIp(String loginLastIp) {
		this.loginLastIp = loginLastIp;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getRiskRating() {
		return riskRating;
	}

	public void setRiskRating(Integer riskRating) {
		this.riskRating = riskRating;
	}

	public String getOperatorPermissions() {
		return operatorPermissions;
	}

	public void setOperatorPermissions(String operatorPermissions) {
		this.operatorPermissions = operatorPermissions;
	}
}
