package com.gta.train.platform.saas.entity.system;

import javax.persistence.Column;
import javax.persistence.Table;

import com.gta.train.platform.persis.mybatis.model.Entity;


/**
 * @author: huan.xu
 * @version: 1.0
 * @since 2019-04-28
 */
@Table(name = "t_system")
public class System extends Entity {
	private static final long serialVersionUID = 1L;

	    /**系统在平台的编码*/
	@Column(name = "code")
	private String code;

	    /**名称*/
	@Column(name = "name")
	private String name;

	    /**域名*/
	@Column(name = "realm_name")
	private String realmName;

	    /**登录地址*/
	@Column(name = "login_url")
	private String loginUrl;

	    /**权限类型适配不同平台的权限类型*/
	@Column(name = "permission_type")
	private Integer permissionType;

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

	

	    /**系统在平台的编码*/
	public String getCode(){

		return code;
	}

	    /**名称*/
	public String getName(){

		return name;
	}

	    /**域名*/
	public String getRealmName(){

		return realmName;
	}

	    /**登录地址*/
	public String getLoginUrl(){

		return loginUrl;
	}

	    /**权限类型适配不同平台的权限类型*/
	public Integer getPermissionType(){

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

	
	    /**系统在平台的编码*/
	public void setCode (String code){

		this.code = code;
	}

	    /**名称*/
	public void setName (String name){

		this.name = name;
	}

	    /**域名*/
	public void setRealmName (String realmName){

		this.realmName = realmName;
	}

	    /**登录地址*/
	public void setLoginUrl (String loginUrl){

		this.loginUrl = loginUrl;
	}

	    /**权限类型适配不同平台的权限类型*/
	public void setPermissionType (Integer permissionType){

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