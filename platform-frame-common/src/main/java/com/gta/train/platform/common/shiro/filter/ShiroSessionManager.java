package com.gta.train.platform.common.shiro.filter;

import java.io.Serializable;

import javax.servlet.ServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ShiroSessionManager extends DefaultWebSessionManager {
	private static Logger logger = LoggerFactory.getLogger(ShiroSessionManager.class);
	/**
     * 获取session
     * 优化单次请求需要多次访问redis的问题
     * @param sessionKey
     * @return
     * @throws UnknownSessionException
     */
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);
     
        ServletRequest request = null;
        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }

        if (request != null && null != sessionId) {
            Object sessionObj = request.getAttribute(sessionId.toString());
            if (sessionObj != null) {
                return (Session) sessionObj;
            }
        }

        Session session = super.retrieveSession(sessionKey);
        
       /* if (request instanceof ShiroHttpServletRequest) {
        	ShiroHttpServletRequest  shiroHttpServletRequest=(org.apache.shiro.web.servlet.ShiroHttpServletRequest) request;
        	logger.debug ("请求路径："+shiroHttpServletRequest.getRequestURI());
        }*/
        
        if (request != null && null != sessionId) {
            request.setAttribute(sessionId.toString(), session);
        }
        return session;
    }
}
