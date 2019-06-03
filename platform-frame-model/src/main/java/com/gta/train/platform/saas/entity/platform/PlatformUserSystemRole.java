package com.gta.train.platform.saas.entity.platform;

import javax.persistence.Column;
import javax.persistence.Table;

import com.gta.train.platform.persis.mybatis.model.Entity;


/**
 * @author: huan.xu
 * @version: 1.0
 * @since 2019-04-28
 */
@Table(name = "t_platform_user_system_role")
public class PlatformUserSystemRole extends Entity {
	private static final long serialVersionUID = 1L;

	    /**系统角色id*/
	@Column(name = "system_role_id")
	private String systemRoleId;

	    /**平台用户id*/
	@Column(name = "platform_user_id")
	private String platformUserId;

	

	    /**系统角色id*/
	public String getSystemRoleId(){

		return systemRoleId;
	}

	    /**平台用户id*/
	public String getPlatformUserId(){

		return platformUserId;
	}

	
	    /**系统角色id*/
	public void setSystemRoleId (String systemRoleId){

		this.systemRoleId = systemRoleId;
	}

	    /**平台用户id*/
	public void setPlatformUserId (String platformUserId){

		this.platformUserId = platformUserId;
	}

	
}