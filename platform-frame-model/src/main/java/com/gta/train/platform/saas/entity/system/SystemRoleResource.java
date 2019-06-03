package com.gta.train.platform.saas.entity.system;

import javax.persistence.Column;
import javax.persistence.Table;

import com.gta.train.platform.persis.mybatis.model.Entity;


/**
 * @author: huan.xu
 * @version: 1.0
 * @since 2019-04-28
 */
@Table(name = "t_system_role_resource")
public class SystemRoleResource extends Entity {
	private static final long serialVersionUID = 1L;

	    /**系统角色id*/
	@Column(name = "system_role_id")
	private String systemRoleId;

	    /**系统资源id*/
	@Column(name = "system_resource_id")
	private String systemResourceId;

	

	    /**系统角色id*/
	public String getSystemRoleId(){

		return systemRoleId;
	}

	    /**系统资源id*/
	public String getSystemResourceId(){

		return systemResourceId;
	}

	
	    /**系统角色id*/
	public void setSystemRoleId (String systemRoleId){

		this.systemRoleId = systemRoleId;
	}

	    /**系统资源id*/
	public void setSystemResourceId (String systemResourceId){

		this.systemResourceId = systemResourceId;
	}

	
}