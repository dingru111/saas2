package com.gta.train.platform.saas.entity.platform;

import javax.persistence.Column;
import javax.persistence.Table;

import com.gta.train.platform.persis.mybatis.model.Entity;


/**
 * @author: huan.xu
 * @version: 1.0
 * @since 2019-04-28
 */
@Table(name = "t_profession")
public class Profession extends Entity {
	private static final long serialVersionUID = 1L;

	    /**名称名称*/
	@Column(name = "name")
	private String name;

	    /**类别类别：0专业1行业*/
	@Column(name = "type")
	private Integer type;

	    /**创建时间创建时间*/
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

	

	    /**名称名称*/
	public String getName(){

		return name;
	}

	    /**类别类别：0专业1行业*/
	public Integer getType(){

		return type;
	}

	    /**创建时间创建时间*/
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

	
	    /**名称名称*/
	public void setName (String name){

		this.name = name;
	}

	    /**类别类别：0专业1行业*/
	public void setType (Integer type){

		this.type = type;
	}

	    /**创建时间创建时间*/
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