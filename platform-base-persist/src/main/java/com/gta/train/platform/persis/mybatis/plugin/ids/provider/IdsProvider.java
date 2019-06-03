/*******************************************************************************
 * Copyright (c) 2017 by JoyLau. All rights reserved
 ******************************************************************************/

package com.gta.train.platform.persis.mybatis.plugin.ids.provider;

 
import org.apache.ibatis.mapping.MappedStatement;

import com.gta.train.platform.persis.mybatis.plugin.MapperException;

import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Set;

/**
 * 通过 ids 字符串的各种操作
 * <p/>
 * ids 如 "1,2,3"
 *
 */
public class IdsProvider extends MapperTemplate {

    public IdsProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 根据主键字符串进行删除，类中只有存在一个带有@Id注解的字段
     *
     * @param ms
     * @return
     */
    public String deleteByIds(MappedStatement ms) {
        final Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.deleteFromTable(entityClass, tableName(entityClass)));
        Set<EntityColumn> columnList = EntityHelper.getPKColumns(entityClass);
        if (columnList.size() == 1) {
            EntityColumn column = columnList.iterator().next();
            sql.append(" where ");
            sql.append(column.getColumn());
            sql.append(" in (${_parameter})");
        } else {
            throw new MapperException("继承 deleteByIds 方法的实体类[" + entityClass.getCanonicalName() + "]中必须只有一个带有 @Id 注解的字段");
        }
        return sql.toString();
    }

    /**
     * 根据主键字符串进行查询，类中只有存在一个带有@Id注解的字段
     *
     * @param ms
     * @return
     */
    public String selectByIds(MappedStatement ms) {
        final Class<?> entityClass = getEntityClass(ms);
        //将返回值修改为实体类型
        setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.selectAllColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
        Set<EntityColumn> columnList = EntityHelper.getPKColumns(entityClass);
        if (columnList.size() == 1) {
            EntityColumn column = columnList.iterator().next();
            sql.append(" where ");
            sql.append(column.getColumn());
            sql.append(" in (${_parameter})");
        } else {
            throw new MapperException("继承 selectByIds 方法的实体类[" + entityClass.getCanonicalName() + "]中必须只有一个带有 @Id 注解的字段");
        }
        return sql.toString();
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
}
