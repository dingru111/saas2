package com.gta.train.platform.saas.entity.platform;

import javax.persistence.Column;
import javax.persistence.Table;

import com.gta.train.platform.persis.mybatis.model.Entity;


/**
 * @author: huan.xu
 * @version: 1.0
 * @since 2019-04-28
 */
@Table(name = "t_school")
public class School extends Entity {
	private static final long serialVersionUID = 1L;

	    /**名称名称*/
	@Column(name = "name")
	private String name;

	    /**国家id国家id*/
	@Column(name = "country_id")
	private String countryId;

	    /**省份id省份id*/
	@Column(name = "province_id")
	private String provinceId;

	    /**城市id城市id*/
	@Column(name = "city_id")
	private String cityId;

	    /**层次层次：0小学，1初中，2高中，3中职，4高职，5本科，6研究生*/
	@Column(name = "level")
	private Integer level;

	    /**类型类型：0综合类、1理工类、2师范类、3农林类、4政法类、5医药类、6财经类、7民族类、8语言类、9艺术类、10体育类、11军事类、12旅游类院校,13N/A*/
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

	    /**国家id国家id*/
	public String getCountryId(){

		return countryId;
	}

	    /**省份id省份id*/
	public String getProvinceId(){

		return provinceId;
	}

	    /**城市id城市id*/
	public String getCityId(){

		return cityId;
	}

	    /**层次层次：0小学，1初中，2高中，3中职，4高职，5本科，6研究生*/
	public Integer getLevel(){

		return level;
	}

	    /**类型类型：0综合类、1理工类、2师范类、3农林类、4政法类、5医药类、6财经类、7民族类、8语言类、9艺术类、10体育类、11军事类、12旅游类院校,13N/A*/
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

	    /**国家id国家id*/
	public void setCountryId (String countryId){

		this.countryId = countryId;
	}

	    /**省份id省份id*/
	public void setProvinceId (String provinceId){

		this.provinceId = provinceId;
	}

	    /**城市id城市id*/
	public void setCityId (String cityId){

		this.cityId = cityId;
	}

	    /**层次层次：0小学，1初中，2高中，3中职，4高职，5本科，6研究生*/
	public void setLevel (Integer level){

		this.level = level;
	}

	    /**类型类型：0综合类、1理工类、2师范类、3农林类、4政法类、5医药类、6财经类、7民族类、8语言类、9艺术类、10体育类、11军事类、12旅游类院校,13N/A*/
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