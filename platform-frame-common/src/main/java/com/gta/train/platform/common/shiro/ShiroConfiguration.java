package com.gta.train.platform.common.shiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;

import com.gta.train.platform.common.shiro.cache.SessionDAO;
import com.gta.train.platform.common.shiro.cache.ShiroRedisCacheManager;
import com.gta.train.platform.common.shiro.filter.FormWithRoleAuthenticationFilter;
import com.gta.train.platform.common.shiro.filter.KickOutFilter;
import com.gta.train.platform.common.shiro.filter.LogoutFilter;
import com.gta.train.platform.common.shiro.filter.RolesOrFilter;
import com.gta.train.platform.common.shiro.filter.SessionFilter;
import com.gta.train.platform.common.shiro.util.CacheKickOut;

@Configuration
public class ShiroConfiguration {
	@Autowired
	private SessionDAO sessionDAO;
	@Autowired
	public SysTemShiroRealm sysTemShiroRealm;

	@Value("${gta.shiro.loginUrl}")
	private String loginUrl;
	@Value("${gta.shiro.successUrl}")
	private String successUrl;
	@Value("${gta.shiro.unauthorizedUrl}")
	private String unauthorizedUrl;
	@Value("${gta.shiro.logout}")
	private String logout;
	@Value("${gta.shiro.kickedUserMap}")
	private  String KickOut;
	@Bean
	public SessionDAO getSessionDAO() {
		return new SessionDAO();
	}

	@Bean
	public SessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new ShiroSessionManager();//ShiroSessionManager
		sessionManager.setSessionValidationSchedulerEnabled(true);
		sessionManager.setSessionIdCookieEnabled(true);
		// ShiroSessionManager shiroSessionManager = new ShiroSessionManager();
		sessionManager.setSessionDAO(getSessionDAO());
		sessionManager.setSessionIdCookieEnabled(true);
		sessionManager.setGlobalSessionTimeout(1800000);//1800000 
		sessionManager.setDeleteInvalidSessions(true);
		sessionManager.setSessionValidationSchedulerEnabled(true);
		sessionManager.setSessionValidationInterval(1800000);// 600000 

		sessionManager.setSessionIdCookie(new SimpleCookie("joylauid"));
		sessionManager.setSessionIdUrlRewritingEnabled(false);
		Collection<SessionListener> listeners = new ArrayList<>();
		sessionManager.setSessionListeners(listeners);

		return sessionManager;
	}

	@Bean
	public org.apache.shiro.mgt.SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		AuthorizingRealm userRealm = (AuthorizingRealm) getSysTemShiroRealm();
		//userRealm.setCachingEnabled(true);
		//userRealm.setAuthenticationCachingEnabled(true);
		//userRealm.setAuthorizationCachingEnabled(true);
		securityManager.setRealm(userRealm);
		securityManager.setCacheManager(shiroRedisCacheManager());
		// 自定义session管理 使用redis
		// customSessionDAO.setCacheManager(shiroRedisCacheManager);

		securityManager.setSessionManager(sessionManager());
		// 注入记住我管理器;
		// securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}

 
	@Autowired
	private RedisTemplate<byte[], Object> shiroRedisTemplate;

	@Bean
	@DependsOn(value = "redisTemplate")
	public ShiroRedisCacheManager shiroRedisCacheManager() {
		return new com.gta.train.platform.common.shiro.cache.ShiroRedisCacheManager(shiroRedisTemplate);
	}

	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件问题。 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
	 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
	 *
	 * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
	 * 3、部分过滤器可指定参数，如perms，roles
	 *
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面 loginUrl
		shiroFilterFactoryBean.setLoginUrl("/nologin/main?out=1");

		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl(successUrl);
		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
		// 自定义拦截器
		Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
		// 限制同一帐号同时在线的个数。
		// filtersMap.put("kickout", kickoutSessionControlFilter());
		// filtersMap.put("authc", kickoutSessionControlFilter());
		filtersMap.put("formAuthc", getFormWithRoleAuthenticationFilter());
		filtersMap.put("roles", getRolesFilter());
		filtersMap.put("logout", getLogoutFilter());
		// filtersMap.put("rolesOr", getRolesOrFilter());
		filtersMap.put("session", getSessionFilter());
		// filtersMap.put("kickout",kickOutFilter());
		shiroFilterFactoryBean.setFilters(filtersMap);
		// 权限控制map.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/commons/**", "anon");
		filterChainDefinitionMap.put("/images/**", "anon");
		filterChainDefinitionMap.put("/nologin/**", "anon");
		filterChainDefinitionMap.put("/loginSubmit/**", "anon");
		//添加一个以渔有方过来的url，当时忘了前面加nologin
		filterChainDefinitionMap.put("/loginFormYyyf/**", "anon");
	/*	filterChainDefinitionMap.put("/index", "anon");
		filterChainDefinitionMap.put("/loginSubmit", "anon");*/
		
	/*	filterChainDefinitionMap.put("/admin/index", "formAuthc");*/
		filterChainDefinitionMap.put("/loginSubmit", "formAuthc");
		
		
		filterChainDefinitionMap.put(logout, "logout");
		if("EBANKkickedUserMap".equals(KickOut)) {
			filterChainDefinitionMap.put("/company/admin/**", "roles[companyAdmin],session");
			filterChainDefinitionMap.put("/company/operator/**", "roles[companyOperator],session");
		}else {
			filterChainDefinitionMap.put("/company/**", "roles[company],session");
		}
		
		filterChainDefinitionMap.put("/admin/**", "roles[admin],session");
		filterChainDefinitionMap.put("/person/**", "roles[person],session");
		filterChainDefinitionMap.put("/superadmin/**", "roles[superadmin],session");

 

		/**
		 * =session // 配置不会被拦截的链接 顺序判断 // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了 // 从数据库获取动态的权限
		 * // filterChainDefinitionMap.put("/add", "perms[权限添加]"); // <!--
		 * 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了; // <!--
		 * authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问--> //logout这个拦截器是shiro已经实现好了的。 //
		 * 从数据库获取 /*List<SysPermissionInit> list = sysPermissionInitService.selectAll();
		 * 
		 * for (SysPermissionInit sysPermissionInit : list) {
		 * filterChainDefinitionMap.put(sysPermissionInit.getUrl(),
		 * sysPermissionInit.getPermissionInit()); }
		 */

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	public SysTemShiroRealm getSysTemShiroRealm() {
		return sysTemShiroRealm;
	}

	/**
	 * cookie对象;
	 * 
	 * @return
	 */
	public SimpleCookie rememberMeCookie() {
		// 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		// <!-- 记住我cookie生效时间30天 ,单位秒;-->
		simpleCookie.setMaxAge(2592000);
		return simpleCookie;
	}

	/**
	 * cookie管理对象;记住我功能
	 * 
	 * @return
	 */
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		// rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
		cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
		return cookieRememberMeManager;
	}

	public FormWithRoleAuthenticationFilter getFormWithRoleAuthenticationFilter() {
		return new FormWithRoleAuthenticationFilter(sessionDAO);// customSessionDAO
	}

	@Bean
	public RolesAuthorizationFilter getRolesFilter() {
		return new RolesAuthorizationFilter();
	}

	@Bean
	public RolesOrFilter getRolesOrFilter() {
		return new RolesOrFilter();
	}

	public LogoutFilter getLogoutFilter() {
		return new LogoutFilter();
	}

	@Bean
	public SessionFilter getSessionFilter() {
		return new SessionFilter();
	}

	//@Bean
	public KickOutFilter kickOutFilter() {
		return new KickOutFilter();
	}

	//@Bean
	public CacheKickOut kickOut() {
		return new CacheKickOut(shiroRedisCacheManager());
	}
}
