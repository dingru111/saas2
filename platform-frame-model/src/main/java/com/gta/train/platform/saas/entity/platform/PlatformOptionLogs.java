package com.gta.train.platform.saas.entity.platform;

import javax.persistence.Column;
import javax.persistence.Table;

import com.gta.train.platform.persis.mybatis.model.Entity;


/**
 * @author: huan.xu
 * @version: 1.0
 * @since 2019-04-28
 */
@Table(name = "t_platform_option_logs")
public class PlatformOptionLogs extends Entity {
	private static final long serialVersionUID = 1L;

	    /**平台用户id*/
	@Column(name = "platform_user_id")
	private String platformUserId;

	    /**姓名*/
	@Column(name = "user_name")
	private String userName;

	    /**工号*/
	@Column(name = "user_code")
	private String userCode;

	    /**角色名称*/
	@Column(name = "role_name")
	private String roleName;

	    /**操作类型1添加 2修改3 删除 4查看 5导出 6重置密码*/
	@Column(name = "opt_type")
	private Integer optType;

	    /**日志详情*/
	@Column(name = "logs")
	private String logs;

	    /**操作结果*/
	@Column(name = "is_success")
	private Integer isSuccess;

	    /**操作时间*/
	@Column(name = "opt_time")
	private java.util.Date optTime;

	

	    /**平台用户id*/
	public String getPlatformUserId(){

		return platformUserId;
	}

	    /**姓名*/
	public String getUserName(){

		return userName;
	}

	    /**工号*/
	public String getUserCode(){

		return userCode;
	}

	    /**角色名称*/
	public String getRoleName(){

		return roleName;
	}

	    /**操作类型1添加 2修改3 删除 4查看 5导出 6重置密码*/
	public Integer getOptType(){

		return optType;
	}

	    /**日志详情*/
	public String getLogs(){

		return logs;
	}

	    /**操作结果*/
	public Integer getIsSuccess(){

		return isSuccess;
	}

	    /**操作时间*/
	public java.util.Date getOptTime(){

		return optTime;
	}

	
	    /**平台用户id*/
	public void setPlatformUserId (String platformUserId){

		this.platformUserId = platformUserId;
	}

	    /**姓名*/
	public void setUserName (String userName){

		this.userName = userName;
	}

	    /**工号*/
	public void setUserCode (String userCode){

		this.userCode = userCode;
	}

	    /**角色名称*/
	public void setRoleName (String roleName){

		this.roleName = roleName;
	}

	    /**操作类型1添加 2修改3 删除 4查看 5导出 6重置密码*/
	public void setOptType (Integer optType){

		this.optType = optType;
	}

	    /**日志详情*/
	public void setLogs (String logs){

		this.logs = logs;
	}

	    /**操作结果*/
	public void setIsSuccess (Integer isSuccess){

		this.isSuccess = isSuccess;
	}

	    /**操作时间*/
	public void setOptTime (java.util.Date optTime){

		this.optTime = optTime;
	}

	
}