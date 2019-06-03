package com.gta.train.platform.config.shiro;


import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import java.util.*;


 
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.subject.WebSubject;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
 
import com.gta.train.platform.common.base.dto.LoginUserInfo;
import com.gta.train.platform.common.shiro.Constant;
import com.gta.train.platform.common.shiro.SysTemShiroRealm;
import com.gta.train.platform.common.shiro.cache.SessionDAO;
import com.gta.train.platform.common.shiro.token.BankPayToken;
import com.gta.train.platform.common.shiro.util.SessionUtil;
import com.gta.train.platform.common.util.JedisClusterUtil;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


public class MyShiroRealm extends AuthorizingRealm implements SysTemShiroRealm {
    
    @Autowired
    private SessionDAO sessionDAO;
    private static StringBuffer LOGIN=new StringBuffer("LOGIN_EBANK");
    private static Long LOGINTIME=1800l;//登录过期时间
    
    private static final Logger LOG = Logger.getLogger(MyShiroRealm.class);
   
    /**
     * @author bohai.wang
     * @version:-1
     * @Description: 踢人操作
     * @author: wbh 
     * @date: datedate{time}
     * @param platformUserId
     * @param sessionId
     */
    public  void removeSession(String platformUserId,String sessionId,BankPayToken token)  {  
    	 
    	Map<Object, Object> kickedUserMap = (HashMap<Object, Object>)JedisClusterUtil.getObject(Constant.KickOut);;
 	    if(kickedUserMap!=null){//存在映射代表已经登录
 	    	String kickedSessionStr = (String)kickedUserMap.get(platformUserId.toLowerCase());
 	    	if(StringUtils.isNotBlank(kickedSessionStr)){
 	    		String[] param = kickedSessionStr.split(",");
 	    		String kickedUserId = param[0];
 	    		String kickedSessionId = param[1];
 	    		
 	    		if(!sessionId.equals(kickedSessionId)) {//sessionid有变化 是异地登录 踢出以前的用户
 	    			sessionDAO.getCache().remove(kickedSessionId); //获得id踢人
 	    			System.err.println("平台platformUserId"+platformUserId+"踢人"+kickedSessionId);
 	    		}else {
 	    		/*	if(SessionUtil.getSession()!=null) {
 	    	    		LoginUserInfo loginUserInfoBefore=SessionUtil.getLoginUser();//之前登录用户
 	    	    		if(loginUserInfoBefore!=null) {
 	    	    			if(
 	    	    					("2".equals(token.getClient()) )&&//当前登录是公司的操作员和管理员
 	    	    					("2".equals(loginUserInfoBefore.getClient()))//以前前登录是公司的操作员和管理员
 	    	    					) {//本次登录和上次登录的都是企业端
 	    	    				if(!token.getUserSide().equals(loginUserInfoBefore.getUserSide())) {//当前登录的公司角色和之前登录的公司角色不一样 
 	    	    					//sessionDAO.getCache().remove(kickedSessionId); //获得id踢人
 	    	    					SessionUtil.logout();
 	    	    					System.err.println(token.getUserSide()+"-"+loginUserInfoBefore.getUserSide()+"-平台platformUserId"+platformUserId+"踢人"+kickedSessionId);
 	    	    				}
 	    	    			}
 	    	    		}
 	    	    		 
 	    	    	}*/
 	    		}
 	    	}
 	    }else{
 	    	kickedUserMap = new HashMap<Object, Object>();
 	     
 	    }
 		System.err.println("新session："+sessionId);
 		kickedUserMap.put(platformUserId.toLowerCase(), platformUserId+","+sessionId); 
 		JedisClusterUtil.set(Constant.KickOut,kickedUserMap); 
    	
    }
    /**
     * 授权用户权限 两个项目都一样 注意 client;// 1、个人用户 2、企业 3、管理员
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        LOG.info("授权验证");
        System.err.println("授权验证");
        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
        if (principals == null || principals.isEmpty()) {
            LOG.info("已经授权");
            return auth;
        }

        LoginUserInfo user = (LoginUserInfo)getAvailablePrincipal(principals);
        if (user != null) {
            LOG.info("没有授权");
            //获取用户角色
            Set<String> roleSet = new HashSet<String>();
            if("1".equals(user.getClient())) {
                roleSet.add("person");
                LOG.info("个人用户授权");
            }
            if("2".equals(user.getClient())) {
                
                LOG.info("公司用户授权");
               if("1".equals(user.getUserSide())) {//公司操作员
            	   roleSet.add("companyOperator");
               } 
               if("2".equals(user.getUserSide())) {//公司管理员
            	   roleSet.add("companyAdmin");
               } 
            }
            if("3".equals(user.getClient())) {
                roleSet.add("admin");
                LOG.info("银行用户授权");
            }
            if("9".equals(user.getClient())) {
                roleSet.add("superadmin");
                LOG.info("superadmin授权");
            }
            auth.setRoles(roleSet);
            //获取用户权限
            Set<String> permissionSet = new HashSet<String>();
            auth.setStringPermissions(permissionSet);
        }


     /*   //获取用户
    	LoginUserInfo user = (LoginUserInfo)SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();*/



        return auth;
    }
    private String getKey(String platformUserId) {
        return "LOGIN_EBANK_"+platformUserId;
    }
    public static Map map = new HashMap();
    /**
     * 验证用户身份 这里改写登录userSide  // 0、个人用户 1、企业操作员 2、企业管理员 3、银行管理员
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
    	BankPayToken token = (BankPayToken) authcToken;
    	String platformUserId=token.getPlatformUserId();
    	//removeSession(  platformUserId);
    	if(SessionUtil.getSession()!=null) {
    		String sessionId=SessionUtil.getSession().getId().toString();
    		System.err.println("shirosessionId:"+sessionId);
    	}
    
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
      
      LoginUserInfo loginUserInfo=null;
    /*   LoginUserInfo loginUserInfo =  SessionUtil.getLoginUser();
        if(loginUserInfo!=null) {
            SecurityUtils.getSubject().logout();
        } */
      	

         ServletRequest servletRequest = ((WebSubject) SecurityUtils.getSubject()).getServletRequest();
        System.err.println("httpsessionId:"+((HttpServletRequest)servletRequest).getSession().getId());
         
        if("8".equals(token.getUserSide()) || "9".equals(token.getUserSide())) {
            LOG.info("superaddmin验证 9平台管理员 8平台教师管理员");
            if(loginUserInfo==null)
                loginUserInfo =new LoginUserInfo();
            loginUserInfo.setPassword(new String(token.getPassword()));
            loginUserInfo.setUserId(token.getPlatformUserId());
        }
        /**构建loginUserInfo*/
        loginUserInfo.setUserSide(token.getUserSide());
        loginUserInfo.setClient(token.getClient());
        loginUserInfo.setPlatformUserId(token.getPlatformUserId());
        loginUserInfo.setUsername(username);
      

       //Collection<Session> sessions = customSessionDAO.getActiveSessions();
       /* Cache<String, SimpleAuthenticationInfo> cache = getAuthenticationInfosCache();
        Collection<SimpleAuthenticationInfo> c= cache.values();
        for (SimpleAuthenticationInfo simpleAuthenticationInfo : c) {
        	System.out.println(simpleAuthenticationInfo);
		}*/
        //盐
        //ByteSource salt = ByteSource.Util.bytes("12212");
        // password盐加密

        //;
        //String userPassword= new Sha512Hash(loginUserInfo.getPassword(), salt).toString();
        // token盐加密
        //password= new Sha512Hash(password, salt).toString();
        // token.setPassword(password.toCharArray());
        AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(loginUserInfo, loginUserInfo.getPassword(),  getName());
    	super.clearCachedAuthorizationInfo(authcInfo.getPrincipals());
    	//SessionUtil.logout();
    	
    	
    
    	
    	String sessionId=SessionUtil.getSession().getId().toString();
    	
    	removeSession(  platformUserId,sessionId,token);
    	SessionUtil.setLoginUser(loginUserInfo);
        return authcInfo;
    }
    /**
     * @description
     * @author wbh
     * @date 2018年11月9日 下午1:21:53
     * @param token
     * @param userId
     * @return
     * LoginUserInfo
     */
    private LoginUserInfo getAdminLoginUserInfo(BankPayToken token,String userId) {
        String loginUserInfoStr = JedisClusterUtil.get(getKey(token.getPlatformUserId()));
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        LoginUserInfo loginUserInfo = gson.fromJson(loginUserInfoStr, new TypeToken<LoginUserInfo>() {}.getType());
        String password="111111";
        if (loginUserInfo == null) {//没有值第一登录
            loginUserInfo = new LoginUserInfo();
            loginUserInfo.setPlatformUserId(token.getPlatformUserId());
            if(userId == null){//学生首次进入系统
                //新增管理员用户
     
            }
        }

        loginUserInfo.setBankUserId(userId);
        loginUserInfo.setPassword(password);

        loginUserInfoStr = gson.toJson(loginUserInfo);
        JedisClusterUtil.set(getKey(token.getPlatformUserId()), loginUserInfoStr, LOGINTIME);
        return loginUserInfo;
    }
     
   



    /**
     * 清除授权缓存
     */
    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除验证缓存
     */
    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }


    /*获取身份认证信息*/
    SimpleAuthenticationInfo getSimpleAuthenticationInfo(String authenticationCacheKey) {
        return getAuthenticationInfosCache().get(authenticationCacheKey);
    }

    /*身份认证信息缓存*/
    private Cache<String, SimpleAuthenticationInfo> getAuthenticationInfosCache() {
        return getCacheManager().getCache(getAuthenticationCacheName());
    }

 

    /**
     * 用户认证信息缓存key的改造，此方法是登录时的改造
     *
     */
    @Override
    protected Object getAuthenticationCacheKey(PrincipalCollection principals) {
        return getRealmCacheKey(principals);
    }
    /**
     * 登录和权限的key统一使用此方法产生,保持统一用户的唯一性。
     *
     */
    private Object getRealmCacheKey(PrincipalCollection principals) {
        LoginUserInfo info = (LoginUserInfo) principals.getPrimaryPrincipal();
        return "LOGIN_EBANK"+info.getPlatformUserId();
    }

    /**
     * 用户权限信息缓存key的改造，此方法是登录时的改造
     *
     */
    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        return getRealmCacheKey(principals);
    }

    /**
     * 通过token 来获得
     *
     */
    @Override
    protected Object getAuthenticationCacheKey(AuthenticationToken token) {
        BankPayToken identityToken = (BankPayToken) token;
        return token != null ? identityToken.getPlatformUserId() : null;
    }




    /*清除身份认证信息缓存和session缓存*/
    public void clearAuthenticationCache(String authenticationCacheKey){
        authenticationCacheKey = authenticationCacheKey.toLowerCase();
        getAuthenticationInfosCache().remove(authenticationCacheKey);
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for(Session session : sessions){
            SimplePrincipalCollection principalCollection = (SimplePrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if(principalCollection!=null){
                LoginUserInfo session_user = (LoginUserInfo)principalCollection.getPrimaryPrincipal();
                if(null != session && StringUtils.equals(session_user.getPlatformUserId(), authenticationCacheKey)){
                	sessionDAO.clearSession(session.getId());
                }
            }
        }
    }
    @Override
    public void setCredentialsMatcher(HashedCredentialsMatcher hashedCredentialsMatcher) {
        // TODO 自动生成的方法存根
        super.setCredentialsMatcher(hashedCredentialsMatcher);
        
    }
}
 
