package com.gta.train.platform.common.shiro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component  
@PropertySource("classpath:application.properties")
public class Constant {
	
	public static String personLogin; 
    
	public static String companyLogin;
	
	public static String KickOut;
    public static String adminLogin;
    @Value("${gta.shiro.personLogin}")
    public   void setPersonLogin(String personLogin) {
		Constant.personLogin = personLogin;
	}
    @Value("${gta.shiro.companyLogin}")
	public void setCompanyLogin(String companyLogin) {
		this.companyLogin = companyLogin;
	}
    @Value("${gta.shiro.adminLogin}")
	public   void setAdminLogin(String adminLogin) {
		Constant.adminLogin = adminLogin;
	}
    @Value("${gta.shiro.kickedUserMap}")
	public   void setKickOut(String kickOut) {
		Constant.KickOut = kickOut;
		
	}
	public   String getPersonLogin() {
		return personLogin;
	}
	public String getCompanyLogin() {
		return companyLogin;
	}
	public   String getAdminLogin() {
		return adminLogin;
	}
	public static String getKickOut() {
		return KickOut;
	}
	
	
}
