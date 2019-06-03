package com.gta.train.platform.saas.entity.system;

import javax.persistence.Column;
import javax.persistence.Table;

import com.gta.train.platform.persis.mybatis.model.Entity;


/**
 * @author: huan.xu
 * @version: 1.0
 * @since 2019-04-28
 */
@Table(name = "t_system_resource")
public class SystemResource extends Entity {
	private static final long serialVersionUID = 1L;

	    /**系统id*/
	@Column(name = "system_id")
	private String systemId;

	    /**系统资源名称*/
	@Column(name = "resource_name")
	private String resourceName;

	    /**上级资源id*/
	@Column(name = "pid")
	private String pid;

	    /**是否是权限节点*/
	@Column(name = "is_permission")
	private Integer isPermission;

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

	    /**系统资源名称*/
	public String getResourceName(){

		return resourceName;
	}

	    /**上级资源id*/
	public String getPid(){

		return pid;
	}

	    /**是否是权限节点*/
	public Integer getIsPermission(){

		return isPermission;
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

	    /**系统资源名称*/
	public void setResourceName (String resourceName){

		this.resourceName = resourceName;
	}

	    /**上级资源id*/
	public void setPid (String pid){

		this.pid = pid;
	}

	    /**是否是权限节点*/
	public void setIsPermission (Integer isPermission){

		this.isPermission = isPermission;
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