package com.gta.train.platform.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.gta.train.platform.BaseTest;
import com.gta.train.platform.common.base.dto.LoginUserInfo;
import com.gta.train.platform.common.shiro.token.BankPayToken;

public class LoginTest  extends BaseTest {
	
	@Test
	public void loginTest() {/*
		//0、个人用户 1、企业操作员 2、企业管理员 3、银行管理员
	/*	BankPayToken usernamePasswordToken = new BankPayToken("个人用户 ", "psw","0",false, "asdasd");
		Subject subject = SecurityUtils.getSubject();
		subject.login(usernamePasswordToken); // 完成登录
		LoginUserInfo user = (LoginUserInfo) subject.getPrincipal();
		 
		usernamePasswordToken = new BankPayToken("个人用户 ", "psw","1","asdasd");
		subject = SecurityUtils.getSubject();
		subject.login(usernamePasswordToken); // 完成登录
		user = (LoginUserInfo) subject.getPrincipal();
		
		usernamePasswordToken = new BankPayToken("企业操作员 ", "psw","2","asdasd");
		subject = SecurityUtils.getSubject();
		subject.login(usernamePasswordToken); // 完成登录
		user = (LoginUserInfo) subject.getPrincipal();
		
		usernamePasswordToken = new BankPayToken("企业管理员 ", "psw","3","asdasd");
		subject = SecurityUtils.getSubject();
		subject.login(usernamePasswordToken); // 完成登录
		user = (LoginUserInfo) subject.getPrincipal();*/
		 
	}
}
