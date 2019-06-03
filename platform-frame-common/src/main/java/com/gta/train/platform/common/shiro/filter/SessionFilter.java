package com.gta.train.platform.common.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * com.gtafe.shiro
 * ran.li@gtafe.com
 */
public class SessionFilter extends AccessControlFilter {
	 private static final Logger _logger = LoggerFactory.getLogger(SessionFilter.class);
    public SessionFilter() {
    }

    /**
     * 在此处理未登录的请求，已经登录的请求交由后续过滤器链处理
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        _logger.info("SessionFilter:isAccessAllowed");
        return subject.isRemembered() || subject.isAuthenticated();
    }

    /**
     *  处理未登录的请求
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
    	_logger.info("SessionFilter:onAccessDenied");
    	String loginUrl = getLoginUrl();
    	if (isAjax(WebUtils.toHttp(request))) {
            WebUtils.toHttp(response).sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return false;
        }else{
        	  WebUtils.issueRedirect(request, response, loginUrl);
        	 
        }
    	return true;
    }
    
    public static boolean isAjax(ServletRequest request) {
        return ((HttpServletRequest) request).getHeader("X-requested-with") != null
                && "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
    }
}
