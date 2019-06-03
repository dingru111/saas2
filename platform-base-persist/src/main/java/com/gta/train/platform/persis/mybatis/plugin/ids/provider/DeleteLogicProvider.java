package com.gta.train.platform.persis.mybatis.plugin.ids.provider;

import java.util.Set;

import org.apache.ibatis.mapping.MappedStatement;

import com.gta.train.platform.persis.mybatis.plugin.FrameworkConstants;
import com.gta.train.platform.persis.mybatis.plugin.MapperException;

import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

 

public class DeleteLogicProvider   extends MapperTemplate {

    public DeleteLogicProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 只更新is_del字段为1 主键不存在 不会更新记录
     * @param ms
     */
    public String deleteLogic(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.updateTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.updateSetColumns(entityClass, null, true, isNotEmpty()));
        sql.append(SqlHelper.wherePKColumns(entityClass));
        return sql.toString();
    }
    public String deleteLogicList(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
         StringBuilder sql = new StringBuilder();
         sql.append(SqlHelper.updateTable(entityClass, tableName(entityClass)));
        // sql.append(SqlHelper.updateSetColumns(entityClass, null, true, isNotEmpty()));
         Set<EntityColumn> columnList = EntityHelper.getPKColumns(entityClass);
         if (columnList.size() == 1) {
             EntityColumn column = columnList.iterator().next();
             sql.append(" set is_del=");
             sql.append(FrameworkConstants.DEL.IS_DEL.getValue());
             sql.append("  where ");
             sql.append(column.getColumn());
             sql.append(" in  <foreach item=\"item\" index=\"index\" collection=\"array\" " );  
             sql.append(" open=\"(\" separator=\",\" close=\")\">  ");
             sql.append(" #{item} "); 
             sql.append(" </foreach>  ");
         } else {
             throw new MapperException("继承 deleteByIds 方法的实体类[" + entityClass.getCanonicalName() + "]中必须只有一个带有 @Id 注解的字段");
         }
         //System.err.println(sql.toString());
     	 
         return sql.toString();
         
         
         
     }
}
