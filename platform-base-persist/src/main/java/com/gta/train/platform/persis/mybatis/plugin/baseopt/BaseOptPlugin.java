package com.gta.train.platform.persis.mybatis.plugin.baseopt;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.gta.train.platform.persis.mybatis.plugin.FrameworkConstants;
import com.gta.train.platform.persis.mybatis.plugin.baseopt.interfaces.BaseLogicDel;
import com.gta.train.platform.persis.mybatis.plugin.baseopt.interfaces.BaseOptTime;
import com.gta.train.platform.persis.mybatis.plugin.baseopt.interfaces.BaseOptUser;
import com.gta.train.platform.persis.mybatis.plugin.baseopt.interfaces.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Properties;
@Configuration
@Service
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class BaseOptPlugin implements Interceptor {

	//@Autowired
	//private SessionUser sessionUser;

 
	public Object intercept(Invocation invocation) throws Throwable {
		/*  StatementHandler statementHandler = (StatementHandler) invocation.getTarget();  
	        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler); */
		Object[] args=invocation.getArgs();
		MappedStatement mappedStatement = (MappedStatement) args[0];
        Object obj = args[1];
		if(org.apache.ibatis.mapping.SqlCommandType.INSERT.equals(mappedStatement.getSqlCommandType())){
			if(obj   instanceof BaseOptTime) {
				BaseOptTime baseOptTime= (BaseOptTime) obj ;
				Date date= new Date(); 
				baseOptTime.setCreateTime(date);
				//baseOptTime.setUpdateTime(date);
			}
			if(obj   instanceof BaseLogicDel) {
				if (((BaseLogicDel) obj).getIsDel()==null) {
					((BaseLogicDel) obj).setIsDel(FrameworkConstants.DEL.NOT_DEL.getValue());
				}
			}
			if(obj   instanceof BaseOptUser) {
				BaseOptUser  baseOptUser=(BaseOptUser)obj;
				 
			 	 if( StringUtils.isBlank(baseOptUser.getCreateUserId())){
			 		baseOptUser.setCreateUserId(getUserId( )) ; 
				 }
			} 
		}else if(org.apache.ibatis.mapping.SqlCommandType.UPDATE.equals(mappedStatement.getSqlCommandType())){
			if(mappedStatement.getId().endsWith(".deleteLogic")||mappedStatement.getId().endsWith(".deleteLogicList")){
				if(obj   instanceof BaseLogicDel) {
					//if( ((BaseOptUser) obj).getDelUserid()==0||((BaseOptUser) obj).getDelUserid()==null){
				 
					//}
				 	((BaseLogicDel) obj).setIsDel(FrameworkConstants.DEL.IS_DEL.getValue());
				 	if (((BaseLogicDel) obj).getIsDel()==null) {
						((BaseLogicDel) obj).setIsDel(FrameworkConstants.DEL.NOT_DEL.getValue());
					}
				} 
			}else{
				if(obj   instanceof BaseOptTime) {
					BaseOptTime baseOptTime= (BaseOptTime) obj ;
					Date date= new Date(); 
					baseOptTime.setUpdateTime(date);
				}
				if(obj   instanceof BaseOptUser) {
					BaseOptUser  baseOptUser=(BaseOptUser)obj;
					if(StringUtils.isBlank(baseOptUser.getUpdateUserId())){
						baseOptUser.setUpdateUserId(getUserId( )) ; 
					}
				} 
			}
		}
		
		 
	/*	objs[0]
		if(){
			
		}*/
		return invocation.proceed();
	}
	public String getUserId( ){
		/*obj.getClass().getName().contains("");
		Subject subject=SecurityUtils.getSubject();
		if (subject.) {
			
		}*/
		if(getRequest()!=null&&getRequest().getRequestURI()!=null) {
			HttpServletRequest request=getRequest();
			String URI=request.getRequestURI(); 
			//String contextPath = request.getContextPath();
		//	StringBuffer contextPath=new StringBuffer(request.getContextPath());
			UserInfo userInfo=  (UserInfo) SecurityUtils.getSubject().getSession().getAttribute("LOGIN_USER");
			if (URI.startsWith(new StringBuffer(request.getContextPath()).append("/company/").toString())) {
				return userInfo.getCompanyUserId();
			}
			if (URI.startsWith(new StringBuffer(request.getContextPath()).append("/person/").toString())) {
				return userInfo.getPersonUserId();
			}
			if (URI.startsWith(new StringBuffer(request.getContextPath()).append("/admin/").toString())) {
				return userInfo.getBankUserId();
			}
		}
		 
		
		/*UserInfor userInfor= (UserInfor)SecurityUtils.getSubject().getPrincipal();
		if (userInfor==null){
			return 0;
		}else{
			return userInfor.getUserid();
		}*/
		//BaseUser baseUser= (BaseUser)SecurityUtils.getSubject().getPrincipal();
		return null;
	}
 
	public Object plugin(Object target) {
		
		return Plugin.wrap(target, this);
	}
	Properties properties;
 
	public void setProperties(Properties properties) {
		this.properties=properties;
	}
	public static HttpServletRequest getRequest() { 
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder 
		.getRequestAttributes(); 
		
		if(attrs==null) {
			return null; 
		}else {
			return attrs.getRequest(); 
		}
	} 
}
