/******************************************************************************
 * Copyright © 2017 Shenzhen GTA Education Tech Ltd. All rights reserved      *
 ******************************************************************************/

package com.gta.train.platform.common.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.gta.train.platform.common.base.dto.LoginUserInfo;
import com.gta.train.platform.common.shiro.Constant;
import com.gta.train.platform.common.util.JedisClusterUtil;


/**
 * 
 * @author ran.li
 *
 */

@PropertySource({"classpath:application.properties"})
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {

    private static final Logger _logger = LoggerFactory.getLogger(LogoutFilter.class);

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
     
        LoginUserInfo user = (LoginUserInfo)subject.getPrincipal();
        _logger.info("LogoutFilter");
        try {
            subject.logout();
        } catch (SessionException ise) {
            _logger.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
        }
        if(user==null||user.getClient()==null) {
        	issueRedirect(request, response, Constant.adminLogin);
        }else if("1".equals(user.getClient())) {
        	 //删除金额显示缓存
            JedisClusterUtil.delObject("showMoneyMap"+user.getUserId());
        	 issueRedirect(request, response,  Constant.personLogin+"?platformUserId="+user.getPlatformUserId());
        	_logger.info("个人用户登出");
        }else if("2".equals(user.getClient())) {
			 issueRedirect(request, response,  Constant.companyLogin+"?platformUserId="+user.getPlatformUserId());
			_logger.info("公司用户登出");
		}else if("3".equals(user.getClient())) {
			 issueRedirect(request, response,  Constant.adminLogin+"?platformUserId="+user.getPlatformUserId());
			_logger.info("银行用户登出");
		}
        return false;
    }
}