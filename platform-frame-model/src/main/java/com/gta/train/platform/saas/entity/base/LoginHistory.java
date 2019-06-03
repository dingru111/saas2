package com.gta.train.platform.saas.entity.base;

import javax.persistence.Column;
import javax.persistence.Table;

import com.gta.train.platform.persis.mybatis.model.Entity;
import com.gta.train.platform.persis.mybatis.plugin.baseopt.interfaces.BaseOpt;

/**
 * @author: qianqian.zhang
 * @version: 1.0
 * @since 2018-11-05
 */
@Table(name = "t_login_history")
public class LoginHistory extends Entity {
	private static final long serialVersionUID = 1L;

	    /***/  	 
	@Column(name = "operate_id")
	private java.lang.String operateId;
	
	    /***/  	 
	@Column(name = "login_time")
	private java.util.Date loginTime;
	
	    /***/  	 
	@Column(name = "login_ip")
	private java.lang.String loginIp;
	
		 
	
	    /***/  
	public java.lang.String getOperateId(){
	
		return operateId;
	}
	
	    /***/  
	public java.util.Date getLoginTime(){
	
		return loginTime;
	}
	
	    /***/  
	public java.lang.String getLoginIp(){
	
		return loginIp;
	}
	
		
	    /***/
	public void setOperateId (java.lang.String operateId){
	
		this.operateId = operateId;
	}
	
	    /***/
	public void setLoginTime (java.util.Date loginTime){
	
		this.loginTime = loginTime;
	}
	
	    /***/
	public void setLoginIp (java.lang.String loginIp){
	
		this.loginIp = loginIp;
	}
	
	
}