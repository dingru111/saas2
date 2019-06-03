/******************************************************************************
 * Copyright (c) 2017.5.23 by JoyLau.                                         *
 * Copyright © 2017 Shenzhen GTA Education Tech Ltd. All rights reserved      *
 ******************************************************************************/

package com.gta.train.platform.common.shiro.filter;

 
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gta.train.platform.common.base.dto.LoginUserInfo;
import com.gta.train.platform.common.shiro.cache.SessionDAO;
import com.gta.train.platform.common.shiro.util.WebUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Deque;

/**
 * Created by JoyLau on 5/23/2017.
 * com.gtafe.shiro
 * fa.liu@gtafe.com
 */
public class KickOutFilter extends AccessControlFilter {
 


 @Autowired
    private SessionDAO sessionDAO ;
 
    
     
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		// TODO 自动生成的方法存根
		  Subject subject = getSubject(request, response);

	        if (!subject.isAuthenticated()) {
	            return false;
	        }
	        Session session = subject.getSession();
	        Serializable sessionId = session.getId();
	        LoginUserInfo loginUserInfo = (LoginUserInfo) subject.getPrincipal() ;
	       String platformUserId = loginUserInfo.getPlatformUserId();
	        sessionDAO.getCache().remove("");
		return false;
	}
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String loginUrl = getLoginUrl();

        if (WebUtil.isAjax(WebUtils.toHttp(request))) {
            WebUtils.toHttp(response).sendError(HttpServletResponse.SC_EXPECTATION_FAILED);
        } else {
            request.setAttribute("KickOut", "您已被踢出");
            WebUtils.saveRequest(request);
            Cookie[] cookies = WebUtils.toHttp(request).getCookies();

            if(cookies !=null){
	            for (Cookie cookie : cookies) {
	                if("identity".equals(cookie.getName()) && "admin".equals(cookie.getValue())){
	                    loginUrl = "/admin";
	                }
	            }
            }

            WebUtils.issueRedirect(request, response, loginUrl);
        }
        return false;
    }
}
