package com.gta.train.platform.saas.entity.platform;

import javax.persistence.Column;
import javax.persistence.Table;

import com.gta.train.platform.persis.mybatis.model.Entity;


/**
 * @author: huan.xu
 * @version: 1.0
 * @since 2019-04-28
 */
@Table(name = "t_verification_code")
public class VerificationCode extends Entity {
	private static final long serialVersionUID = 1L;

	    /**认证码*/
	@Column(name = "code")
	private String code;

	

	    /**认证码*/
	public String getCode(){

		return code;
	}

	
	    /**认证码*/
	public void setCode (String code){

		this.code = code;
	}

	
}