package com.gta.train.platform.persis.page.plugin;

/**
 * 分页用拦截器
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;


@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
		RowBounds.class, ResultHandler.class }) })
public class PageInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		// 当前环境 MappedStatement，BoundSql，及sql取得
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		// 方法入参
		Object parameter = invocation.getArgs()[1];
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		//模板sql
		String originalSql = boundSql.getSql().trim();
	   
		//方法入参
		Object parameterObject = boundSql.getParameterObject();
 
		// Page对象获取，“信使”到达拦截器！
		Page page = searchPageWithXpath(boundSql.getParameterObject(), ".", "page", "*/page");
		if (parameterObject instanceof com.gta.train.platform.persis.page.PageExample) {
			page=((com.gta.train.platform.persis.page.PageExample) parameterObject).getPage();
		}
		if (page != null) {//存在 Page对象
			//得到总记录数
			int totpage = getCount(mappedStatement, boundSql, originalSql, parameterObject);
			// 分页计算
			page.setTotalRecord(totpage);
			// 对原始Sql追加limit
			int offset = (page.getPageNo() - 1) * page.getPageSize();
			
			StringBuffer sb = new StringBuffer();
			sb.append(originalSql).append(" limit ").append(offset).append(",").append(page.getPageSize());
			BoundSql addPageBoundSql = copyFromBoundSql(mappedStatement, boundSql, sb.toString());
			MappedStatement pageMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(addPageBoundSql));
			//修改为增加 limit分页的 对象
			invocation.getArgs()[0] = pageMs;

			Object resultObj = invocation.proceed();
			if (resultObj instanceof List) {//是列表放入 list
				page.setResults((List) resultObj);
			} else {//对象放入 obj
				page.setEntity(resultObj);
			}
			return resultObj;
		}
		return invocation.proceed();

	}

	public int getCount(MappedStatement mappedStatement, BoundSql boundSql, String originalSql,
			Object parameterObject) {
		// Page对象存在的场合，开始分页处理
		String countSql = getCountSql(originalSql);
		Connection connection = null;
		PreparedStatement countStmt = null;
		ResultSet rs = null;
		BoundSql countBS = copyFromBoundSql(mappedStatement, boundSql, countSql);
		DefaultParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject,
				countBS);
		int totpage = 0;
		try {
			connection = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
			countStmt = connection.prepareStatement(countSql);
			parameterHandler.setParameters(countStmt);
			rs = countStmt.executeQuery();
			if (rs.next()) {
				totpage = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			try {
				if (countStmt != null) {
					countStmt.close();
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

		return totpage;
	}

	/**
	 * 根据给定的xpath查询Page对象
	 */
	private Page searchPageWithXpath(Object o, String... xpaths) {
		if (!(o instanceof IPage)) {
			//System.err.println("非业务对象");
			return null;
		}//System.err.println("业务对象");
		JXPathContext context = JXPathContext.newContext(o);

		Object result=null;
		for (String xpath : xpaths) {
			try {
				result = context.selectSingleNode(xpath);
			} catch (Exception e) {
				 e.printStackTrace();
				continue;
			}
			if (result instanceof Page) {
				return (Page) result;
			}
		}
		return null;
	}

	/**
	 * 复制MappedStatement对象
	 */
	private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
		Builder builder = new Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if(ms.getKeyProperties()!=null){
			builder.keyProperty(ms.getKeyProperties()[0]);
		}
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());
		return builder.build();
	}

	/**
	 * 复制BoundSql对象
	 */
	private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql, String sql) {
		BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(),
				boundSql.getParameterObject());
		for (ParameterMapping mapping : boundSql.getParameterMappings()) {
			String prop = mapping.getProperty();
			if (boundSql.hasAdditionalParameter(prop)) {
				newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
			}
		}
		return newBoundSql;
	}

	/**
	 * 根据原Sql语句获取对应的查询总记录数的Sql语句
	 */
	private String getCountSql(String sql) {
		return "SELECT COUNT(*) FROM (" + sql + ") aliasForPage";
	}

	public class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;

		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		@Override
		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}

	@Override
	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	@Override
	public void setProperties(Properties arg0) {
	}
}