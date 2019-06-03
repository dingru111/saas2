package com.gta.train.platform.saas.entity.platform;

import javax.persistence.Column;
import javax.persistence.Table;

import com.gta.train.platform.persis.mybatis.model.Entity;


/**
 * @author: huan.xu
 * @version: 1.0
 * @since 2019-04-28
 */
@Table(name = "t_platform_option")
public class PlatformOption extends Entity {
	private static final long serialVersionUID = 1L;

	    /**操作名称*/
	@Column(name = "opt_name")
	private String optName;

	    /**操作类型1添加 2修改3 删除 4查看 5导出 6重置密码*/
	@Column(name = "opt_type")
	private Integer optType;

	    /**操作拦截参数*/
	@Column(name = "opt_interceptor")
	private String optInterceptor;

	    /**操作拦截类型1 url 2 切面*/
	@Column(name = "opt_interceptor_type")
	private Integer optInterceptorType;

	

	    /**操作名称*/
	public String getOptName(){

		return optName;
	}

	    /**操作类型1添加 2修改3 删除 4查看 5导出 6重置密码*/
	public Integer getOptType(){

		return optType;
	}

	    /**操作拦截参数*/
	public String getOptInterceptor(){

		return optInterceptor;
	}

	    /**操作拦截类型1 url 2 切面*/
	public Integer getOptInterceptorType(){

		return optInterceptorType;
	}

	
	    /**操作名称*/
	public void setOptName (String optName){

		this.optName = optName;
	}

	    /**操作类型1添加 2修改3 删除 4查看 5导出 6重置密码*/
	public void setOptType (Integer optType){

		this.optType = optType;
	}

	    /**操作拦截参数*/
	public void setOptInterceptor (String optInterceptor){

		this.optInterceptor = optInterceptor;
	}

	    /**操作拦截类型1 url 2 切面*/
	public void setOptInterceptorType (Integer optInterceptorType){

		this.optInterceptorType = optInterceptorType;
	}

	
}