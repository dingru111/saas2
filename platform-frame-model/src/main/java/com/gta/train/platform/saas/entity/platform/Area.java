package com.gta.train.platform.saas.entity.platform;

import javax.persistence.Column;
import javax.persistence.Table;

import com.gta.train.platform.persis.mybatis.model.Entity;


/**
 * @author: huan.xu
 * @version: 1.0
 * @since 2019-04-28
 */
@Table(name = "t_area")
public class Area extends Entity {
	private static final long serialVersionUID = 1L;

	    /**父id*/
	@Column(name = "p_id")
	private String pId;

	    /**名称*/
	@Column(name = "area_name")
	private String areaName;

	    /**排序*/
	@Column(name = "sort")
	private Integer sort;

	    /**地区类型1 国家 2 省 3市*/
	@Column(name = "area_type")
	private Integer areaType;

	

	    /**父id*/
	public String getPId(){

		return pId;
	}

	    /**名称*/
	public String getAreaName(){

		return areaName;
	}

	    /**排序*/
	public Integer getSort(){

		return sort;
	}

	    /**地区类型1 国家 2 省 3市*/
	public Integer getAreaType(){

		return areaType;
	}

	
	    /**父id*/
	public void setPId (String pId){

		this.pId = pId;
	}

	    /**名称*/
	public void setAreaName (String areaName){

		this.areaName = areaName;
	}

	    /**排序*/
	public void setSort (Integer sort){

		this.sort = sort;
	}

	    /**地区类型1 国家 2 省 3市*/
	public void setAreaType (Integer areaType){

		this.areaType = areaType;
	}



	
}