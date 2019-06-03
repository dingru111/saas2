package com.gta.train.platform.common.shiro;

 
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
 

public interface SysTemShiroRealm{

	void setCredentialsMatcher(HashedCredentialsMatcher hashedCredentialsMatcher);
    /*清除身份认证信息*/
    public void clearAuthenticationCache(String authenticationCacheKey);
 
}
 
