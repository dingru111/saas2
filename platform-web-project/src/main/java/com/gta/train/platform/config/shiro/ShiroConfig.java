package com.gta.train.platform.config.shiro;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gta.train.platform.common.shiro.SysTemShiroRealm;

@Configuration
public class ShiroConfig {
@Bean
public SysTemShiroRealm getSysTemShiroRealm() {
	MyShiroRealm myShiroRealm=new MyShiroRealm();
	return  myShiroRealm;
}

}
