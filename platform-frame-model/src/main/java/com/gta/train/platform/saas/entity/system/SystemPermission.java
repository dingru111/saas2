package com.gta.train.platform.saas.entity.system;

import javax.persistence.Column;
import javax.persistence.Table;

import com.gta.train.platform.persis.mybatis.model.Entity;


/**
 * @author: huan.xu
 * @version: 1.0
 * @since 2019-04-28
 */
@Table(name = "t_system_permission")
public class SystemPermission extends Entity {
	private static final long serialVersionUID = 1L;

	    /**系统id*/
	@Column(name = "system_id")
	private String systemId;

	    /**系统资源id*/
	@Column(name = "resource_id")
	private String resourceId;

	    /**权限内容*/
	@Column(name = "permission")
	private String permission;

	    /**内容类型(role,shiro,url)*/
	@Column(name = "permission_type")
	private String permissionType;

	    /**新建时间*/
	@Column(name = "create_time")
	private java.util.Date createTime;

	    /**修改时间*/
	@Column(name = "update_time")
	private java.util.Date updateTime;

	    /**删除标识0 未删除 1已删除*/
	@Column(name = "is_del")
	private Integer isDel;

	    /**新建人id*/
	@Column(name = "create_user_id")
	private String createUserId;

	    /**修改人id*/
	@Column(name = "update_user_id")
	private String updateUserId;

	

	    /**系统id*/
	public String getSystemId(){

		return systemId;
	}

	    /**系统资源id*/
	public String getResourceId(){

		return resourceId;
	}

	    /**权限内容*/
	public String getPermission(){

		return permission;
	}

	    /**内容类型(role,shiro,url)*/
	public String getPermissionType(){

		return permissionType;
	}

	    /**新建时间*/
	public java.util.Date getCreateTime(){

		return createTime;
	}

	    /**修改时间*/
	public java.util.Date getUpdateTime(){

		return updateTime;
	}

	    /**删除标识0 未删除 1已删除*/
	public Integer getIsDel(){

		return isDel;
	}

	    /**新建人id*/
	public String getCreateUserId(){

		return createUserId;
	}

	    /**修改人id*/
	public String getUpdateUserId(){

		return updateUserId;
	}

	
	    /**系统id*/
	public void setSystemId (String systemId){

		this.systemId = systemId;
	}

	    /**系统资源id*/
	public void setResourceId (String resourceId){

		this.resourceId = resourceId;
	}

	    /**权限内容*/
	public void setPermission (String permission){

		this.permission = permission;
	}

	    /**内容类型(role,shiro,url)*/
	public void setPermissionType (String permissionType){

		this.permissionType = permissionType;
	}

	    /**新建时间*/
	public void setCreateTime (java.util.Date createTime){

		this.createTime = createTime;
	}

	    /**修改时间*/
	public void setUpdateTime (java.util.Date updateTime){

		this.updateTime = updateTime;
	}

	    /**删除标识0 未删除 1已删除*/
	public void setIsDel (Integer isDel){

		this.isDel = isDel;
	}

	    /**新建人id*/
	public void setCreateUserId (String createUserId){

		this.createUserId = createUserId;
	}

	    /**修改人id*/
	public void setUpdateUserId (String updateUserId){

		this.updateUserId = updateUserId;
	}

	
}