package com.gta.train.platform.common.shiro;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


 
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
        if (sessionKey instanceof WebSessionKey) {// 是web的session key 获得 request
            request = ((WebSessionKey) sessionKey).getServletRequest();
            //logger.info("URI"+ ((HttpServletRequest) request).getRequestURI());
        }
        /*
         * request sessionId 从 request中取值 。 
         * 注意:
         * 使用HttpRequest不使用HttpSession 是因为过滤当前页面的静态文件。
         * 原因：
         * 一个URL的过滤使用redis获取获取session。
         * 当前页面是HttpRequest有效，将sessinid放入当前请求后当前URL的静态文件资源验证在HttpRequest中做过滤，不用再次访问redis。
         * 下一个URL再从redis获取获取session，验证通过委托给当前HttpRequest。
         * **/
        if (request != null && null != sessionId) {//
            Object sessionObj = request.getAttribute(sessionId.toString());
            if (sessionObj != null) {
            	//logger.debug("request获取获取session");
                return (Session) sessionObj;
            }
        }
       
        
        //logger.debug("redis获取获取session");
        //redis获取获取session
        Session session = super.retrieveSession(sessionKey);
        
       /* if (request instanceof ShiroHttpServletRequest) {
        	ShiroHttpServletRequest  shiroHttpServletRequest=(org.apache.shiro.web.servlet.ShiroHttpServletRequest) request;
        	logger.debug ("请求路径："+shiroHttpServletRequest.getRequestURI());
        }*/
        //sessionId 放入当前页面 HttpRequest 
        if (request != null && null != sessionId) {
            request.setAttribute(sessionId.toString(), session);
        }
        return session;
    }
}

