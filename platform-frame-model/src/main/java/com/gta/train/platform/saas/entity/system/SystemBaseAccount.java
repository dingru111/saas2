package com.gta.train.platform.saas.entity.system;

import javax.persistence.Column;
import javax.persistence.Table;

import com.gta.train.platform.persis.mybatis.model.Entity;


/**
 * @author: huan.xu
 * @version: 1.0
 * @since 2019-04-28
 */
@Table(name = "t_system_base_account")
public class SystemBaseAccount extends Entity {
	private static final long serialVersionUID = 1L;

	    /**账号账号*/
	@Column(name = "account")
	private String account;

	    /**姓名姓名*/
	@Column(name = "name")
	private String name;

	    /**所属系统id所属系统*/
	@Column(name = "system_id")
	private String systemId;

	    /**学校公_id*/
	@Column(name = "t_s_id")
	private String tSId;

	    /**账号创建时间账号创建时间*/
	@Column(name = "sys_create_time")
	private java.util.Date sysCreateTime;

	    /**账号来源账号来源:0用户自己注册，1平台管理员开的，2学校管理员开的*/
	@Column(name = "account_source")
	private Integer accountSource;

	    /**账号角色账号角色:0老师；1学生；2管理员*/
	@Column(name = "account_role")
	private Integer accountRole;

	    /**入学年份入学年份*/
	@Column(name = "enrollment_year")
	private Integer enrollmentYear;

	    /**账号类型账号类型:0正式，1试用,2内部使用*/
	@Column(name = "account_type")
	private Integer accountType;

	    /**账号权限到期时间账号权限到期时间*/
	@Column(name = "expiration_time")
	private java.util.Date expirationTime;

	    /**登录次数登录次数*/
	@Column(name = "login_times")
	private Integer loginTimes;

	    /**学校/公司所在国家id学校/公司所在国家*/
	@Column(name = "country_id")
	private String countryId;

	    /**学校/公司所在省份id学校/公司所在省份*/
	@Column(name = "province_id")
	private String provinceId;

	    /**学校/公司所在城市id*/
	@Column(name = "city_id")
	private String cityId;

	    /**学校/公司名称学校/公司名称*/
	@Column(name = "sch_com_name")
	private String schComName;

	    /**客户类型账号类型:0普通用户；1VIP；2VVIP;3一级管理员;4二级管理员*/
	@Column(name = "customer_type")
	private Integer customerType;

	

	    /**账号账号*/
	public String getAccount(){

		return account;
	}

	    /**姓名姓名*/
	public String getName(){

		return name;
	}

	    /**所属系统id所属系统*/
	public String getSystemId(){

		return systemId;
	}

	    /**学校公_id*/
	public String getTSId(){

		return tSId;
	}

	    /**账号创建时间账号创建时间*/
	public java.util.Date getSysCreateTime(){

		return sysCreateTime;
	}

	    /**账号来源账号来源:0用户自己注册，1平台管理员开的，2学校管理员开的*/
	public Integer getAccountSource(){

		return accountSource;
	}

	    /**账号角色账号角色:0老师；1学生；2管理员*/
	public Integer getAccountRole(){

		return accountRole;
	}

	    /**入学年份入学年份*/
	public Integer getEnrollmentYear(){

		return enrollmentYear;
	}

	    /**账号类型账号类型:0正式，1试用,2内部使用*/
	public Integer getAccountType(){

		return accountType;
	}

	    /**账号权限到期时间账号权限到期时间*/
	public java.util.Date getExpirationTime(){

		return expirationTime;
	}

	    /**登录次数登录次数*/
	public Integer getLoginTimes(){

		return loginTimes;
	}

	    /**学校/公司所在国家id学校/公司所在国家*/
	public String getCountryId(){

		return countryId;
	}

	    /**学校/公司所在省份id学校/公司所在省份*/
	public String getProvinceId(){

		return provinceId;
	}

	    /**学校/公司所在城市id*/
	public String getCityId(){

		return cityId;
	}

	    /**学校/公司名称学校/公司名称*/
	public String getSchComName(){

		return schComName;
	}

	    /**客户类型账号类型:0普通用户；1VIP；2VVIP;3一级管理员;4二级管理员*/
	public Integer getCustomerType(){

		return customerType;
	}

	
	    /**账号账号*/
	public void setAccount (String account){

		this.account = account;
	}

	    /**姓名姓名*/
	public void setName (String name){

		this.name = name;
	}

	    /**所属系统id所属系统*/
	public void setSystemId (String systemId){

		this.systemId = systemId;
	}

	    /**学校公_id*/
	public void setTSId (String tSId){

		this.tSId = tSId;
	}

	    /**账号创建时间账号创建时间*/
	public void setSysCreateTime (java.util.Date sysCreateTime){

		this.sysCreateTime = sysCreateTime;
	}

	    /**账号来源账号来源:0用户自己注册，1平台管理员开的，2学校管理员开的*/
	public void setAccountSource (Integer accountSource){

		this.accountSource = accountSource;
	}

	    /**账号角色账号角色:0老师；1学生；2管理员*/
	public void setAccountRole (Integer accountRole){

		this.accountRole = accountRole;
	}

	    /**入学年份入学年份*/
	public void setEnrollmentYear (Integer enrollmentYear){

		this.enrollmentYear = enrollmentYear;
	}

	    /**账号类型账号类型:0正式，1试用,2内部使用*/
	public void setAccountType (Integer accountType){

		this.accountType = accountType;
	}

	    /**账号权限到期时间账号权限到期时间*/
	public void setExpirationTime (java.util.Date expirationTime){

		this.expirationTime = expirationTime;
	}

	    /**登录次数登录次数*/
	public void setLoginTimes (Integer loginTimes){

		this.loginTimes = loginTimes;
	}

	    /**学校/公司所在国家id学校/公司所在国家*/
	public void setCountryId (String countryId){

		this.countryId = countryId;
	}

	    /**学校/公司所在省份id学校/公司所在省份*/
	public void setProvinceId (String provinceId){

		this.provinceId = provinceId;
	}

	    /**学校/公司所在城市id*/
	public void setCityId (String cityId){

		this.cityId = cityId;
	}

	    /**学校/公司名称学校/公司名称*/
	public void setSchComName (String schComName){

		this.schComName = schComName;
	}

	    /**客户类型账号类型:0普通用户；1VIP；2VVIP;3一级管理员;4二级管理员*/
	public void setCustomerType (Integer customerType){

		this.customerType = customerType;
	}

	
}