package com.gta.train.platform.common.shiro.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.gta.train.platform.common.base.dto.LoginUserInfo;

 

public class SessionUtil {
	public static Session getSession(){
		Subject subject = SecurityUtils.getSubject();
		return subject.getSession();
	}
	public static LoginUserInfo getLoginUser(){
		Subject subject = SecurityUtils.getSubject();
		return (LoginUserInfo) subject.getSession().getAttribute("LOGIN_USER");
	}
	public static void setLoginUser(LoginUserInfo loginUserInfo){
		Subject subject = SecurityUtils.getSubject();
		
		 subject.getSession().setAttribute("LOGIN_USER",loginUserInfo);
	}
	
	public static  void logout(){
		Subject subject = SecurityUtils.getSubject();
		if(subject!=null) {
			subject.logout();
		}
	}
	
	 
	/** 得到登录用户
	 * @return
	 *//*
	public static BaseUser getLoginUser(){
		Subject subject = SecurityUtils.getSubject();
		return (BaseUser) subject.getSession().getAttribute(FrameworkConstants.LOGIN_USER);
	}
	public static BaseUser getLoginUser(){
		Subject subject = SecurityUtils.getSubject();
		return (BaseUser) subject.getSession().getAttribute(FrameworkConstants.LOGIN_USER);
	}
	*//** 的到登录学生
	 * @return
	 *//*
	public static Student getStudent(){
		Subject subject = SecurityUtils.getSubject();
		return (Student) subject.getSession().getAttribute(FrameworkConstants.LOGIN_STUDENT);
	}*/
}
