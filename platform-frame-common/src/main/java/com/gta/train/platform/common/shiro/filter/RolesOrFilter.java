package com.gta.train.platform.common.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Shiro角色异或过滤器
 * 默认的RolesAuthorizationFilter过滤器，roles[role1,role2]表示用户必须同时拥有role1和role2角色
 * 而RolesOrFilter表示用户拥有role1或role2角色都有权限访问
 * @author chao.gao
 *
 */
public class RolesOrFilter extends RolesAuthorizationFilter {
	 private static final Logger _logger = LoggerFactory.getLogger(RolesOrFilter.class);
	@Override
	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {
		  _logger.info("RolesOrFilter");
		Subject subject = getSubject(request, response);
		String[] rolesArray = (String[]) mappedValue;

		if ((rolesArray == null) || (rolesArray.length == 0)) {
			return true;
		}

		for (int i = 0; i < rolesArray.length; i++) {
			
			if( subject.hasRole(rolesArray[i]) )
			{
				//用户只要拥有任何一个角色则验证通过
				return true;
			}
		}
		return true;
	}
}
