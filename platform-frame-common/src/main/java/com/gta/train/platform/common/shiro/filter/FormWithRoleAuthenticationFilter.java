package com.gta.train.platform.common.shiro.filter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import com.gta.train.platform.common.base.dto.LoginUserInfo;
import com.gta.train.platform.common.shiro.Constant;
import com.gta.train.platform.common.shiro.cache.SessionDAO;
import com.gta.train.platform.common.shiro.token.BankPayToken;
import com.gta.train.platform.common.util.JedisClusterUtil;
 


/**
 * 带角色的窗口登录验证过滤器
 * 
 * 
 */
 
public class FormWithRoleAuthenticationFilter extends FormAuthenticationFilter {
	
	  private static final Logger _logger = LoggerFactory.getLogger(FormWithRoleAuthenticationFilter.class);
	  public  FormWithRoleAuthenticationFilter(SessionDAO sessionDAO){
		  this.sessionDAO=sessionDAO;
	  }
	  public  FormWithRoleAuthenticationFilter( ){
		  
	  }
	 /* @Value("${port}")
	  private String kickMapName;*/
	 @Autowired
	 @Qualifier(value="sessionDAO")
	 private SessionDAO sessionDAO;

	/**
	 * 角色请求参数名
	 */
	private String roleParam = "role";

	/**
	 * 错误响应参数名
	 */
	private String errorMessageParam = "errorMessage";

	/**
	 * 生产令牌
	 */
	@Override
	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) {
		 _logger.info("生产令牌");
		 System.err.println("生产令牌");
		String username = getUsername(request);
		String password = getPassword(request);
		String role = getRole(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		 String userSide=WebUtils.getCleanParam(request, "userSide");// 0、个人用户 1、企业操作员 2、企业管理员 3、银行管理员
		 String client=WebUtils.getCleanParam(request, "client");// 1、个人用户 2、企业 3、管理员
		 String platformUserId=WebUtils.getCleanParam(request, "platformUserId");
		
		BankPayToken bankPayToken= new BankPayToken(username, password, role,
				rememberMe, host);
		bankPayToken.setUserSide(userSide);
		bankPayToken.setClient(client);
		bankPayToken.setPlatformUserId(platformUserId);
		return bankPayToken;
	}

	/**
	 * 登录成功
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		 System.err.println("onLoginSuccess");
		Session session = subject.getSession();
	    String sessionId = session.getId().toString();
	    _logger.info("登陆成功："+sessionId);
	    LoginUserInfo user = (LoginUserInfo)subject.getPrincipal();
 
 
	    
	    
    
	    Map<Object, Object> kickedUserMap = (HashMap<Object, Object>)JedisClusterUtil.getObject(Constant.KickOut);;
	    if(kickedUserMap!=null){//存在映射代表已经登录
	    	String kickedSessionStr = (String)kickedUserMap.get(user.getPlatformUserId().toLowerCase());
	    	if(StringUtils.isNotBlank(kickedSessionStr)){
	    		String[] param = kickedSessionStr.split(",");
	    		String kickedUserId = param[0];
	    		String kickedSessionId = param[1];
	    		removeSession(kickedSessionId, kickedUserId);
	    	}
	    }else{
	    	kickedUserMap = new HashMap<Object, Object>();
	     
	    }
		kickedUserMap.put(user.getPlatformUserId().toLowerCase(), user.getPlatformUserId()+","+sessionId); 
		JedisClusterUtil.set(Constant.KickOut,kickedUserMap); 
		
	 
 	// removeSessionByUsername(user.getPlatformUserId().toLowerCase(),sessionId);
	    //用户token 验证通过 还要把 session 中放入用户 信息
	    session.setAttribute("LOGIN_USER", user);
	    issueSuccessRedirect(request, response);
		//WebUtils.getAndClearSavedRequest(request);
	   // WebUtils.redirectToSavedRequest(request, response, "/main.do");

		return false;
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		/**
		 * 登录失败移除用户信息
		 */
		//httpRequest.getSession().removeAttribute(FrameworkConstants.LOGIN_USER);
		setFailureAttribute(request, e);
		//httpRequest.setAttribute(errorMessageParam, e.getMessage());

		return true;
	}

	public String getRoleParam() {
		return roleParam;
	}

	public void setRoleParam(String roleParam) {
		this.roleParam = roleParam;
	}

	/**
	 * 获取角色
	 * 
	 * @param request
	 * @return
	 */
	public String getRole(ServletRequest request) {
		return WebUtils.getCleanParam(request, getRoleParam());
	}

	public String getErrorMessageParam() {
		return errorMessageParam;
	}

	public void setErrorMessageParam(String errorMessageParam) {
		this.errorMessageParam = errorMessageParam;
	}
	
	 /** 
     * @param username 
     * @return 
	 * @throws Exception 
     */  
     public  void removeSessionByUsername(String platformUserId,String sessionId) throws Exception{  
         Collection<Session> sessions = sessionDAO.getActiveSessions();  
        for(Session session : sessions){  
        	if (session == null) {
 				continue;
 			}
        	SimplePrincipalCollection principalCollection = (SimplePrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
         	if(principalCollection!=null){
         		LoginUserInfo session_user = (LoginUserInfo)principalCollection.getPrimaryPrincipal();
            	if(null != session && StringUtils.equals(session_user.getPlatformUserId(), platformUserId)){  
             		if(!StringUtils.equals(session.getId().toString(),sessionId)){
            			sessionDAO.getCache().remove(session.getId().toString()); 
             		 
             		}
                 }  
         	}
         }  
     }
    
    /** 
     * 
     * @param sessionId
     * @param kickedUserId
     * @throws Exception
     */
    public  void removeSession(String sessionId,String kickedUserId) throws Exception{  
    	 
    	sessionDAO.getCache().remove(sessionId); //获得id踢人
    }
}
