package com.gta.train.platform.saas.entity.platform;

import javax.persistence.Column;
import javax.persistence.Table;

import com.gta.train.platform.persis.mybatis.model.Entity;


/**
 * @author: huan.xu
 * @version: 1.0
 * @since 2019-04-28
 */
@Table(name = "t_platform_user")
public class PlatformUser extends Entity {
	private static final long serialVersionUID = 1L;

	    /**机构用户id*/
	@Column(name = "org_user_id")
	private String orgUserId;

	    /**工号冗余字段*/
	@Column(name = "code")
	private String code;

	    /**姓名冗余字段*/
	@Column(name = "name")
	private String name;

	    /**密码*/
	@Column(name = "password")
	private String password;

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

	

	    /**机构用户id*/
	public String getOrgUserId(){

		return orgUserId;
	}

	    /**工号冗余字段*/
	public String getCode(){

		return code;
	}

	    /**姓名冗余字段*/
	public String getName(){

		return name;
	}

	    /**密码*/
	public String getPassword(){

		return password;
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

	
	    /**机构用户id*/
	public void setOrgUserId (String orgUserId){

		this.orgUserId = orgUserId;
	}

	    /**工号冗余字段*/
	public void setCode (String code){

		this.code = code;
	}

	    /**姓名冗余字段*/
	public void setName (String name){

		this.name = name;
	}

	    /**密码*/
	public void setPassword (String password){

		this.password = password;
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