package com.gta.train.platform.common.base.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.gta.train.platform.persis.mybatis.plugin.baseopt.interfaces.BaseLogicDel;

 
public interface FunctionService <T>{



    /**
     * 
     * 
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     */
    int insert(T model);

    /**
     * 
     * 
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     */
    int insertSelective(T model);


    /**
     * 
     * 
     * 批量插入，支持批量插入的数据库可以使用，另外该接口限制实体包含`id`属性并且必须为自增列
     */
    int insertList(List<T> list);

    /**
     * 
     * 
     * 根据主键更新实体全部字段，null值会被更新
     */
    int updateByPrimaryKey(T model);

    /**
     * 
     * 
     * 根据主键更新属性不为null的值
     */
    int updateByPrimaryKeySelective(T model);

    /**
     * 
     * 
     * 根据Example条件更新实体`model`包含的全部属性，null值会被更新
     */
    int updateByExample(T model, Object example);


    /**
     * 
     * 
     * 根据Example条件更新实体`model`包含的不是null的属性值
     */
    int updateByExampleSelective(T model, Object example);

    /**
     * 
     * 
     * 根据实体属性作为条件进行删除，查询条件使用等号
     */
    int delete(T model);

    /**
     * 
     * 
     * 根据实体id删除
     */
    int deleteById(int id);

    /**
     * 
     * 
     * 根据Example条件删除数据
     */
    int deleteByExample(Object example);

    /**
     * 
     * 
     * 根据主键字符串进行删除，类中只有存在一个带有@Id注解的字段
     *
     * @param ids 如 "1,2,3,4"
     */
    int deleteByIds(String ids);

    /**
     * 
     * 
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     */
    int deleteByPrimaryKey(Object key);

    /**
     * 
     * 
     * 根据实体中的属性值进行查询，查询条件使用等号
     */
    List<T> select(T model);


    /**
     * 
     * 
     * 根据实体中的id查询实体
     */
    T selectById(Object id);

    /**
     * 
     * 
     * 查询全部结果
     */
    List<T> selectAll();

    /**
     * 
     * 
     * 根据Example条件进行查询
     */
    List<T> selectByExample(Object example);

    /**
     * 
     * 
     * 根据example条件和RowBounds进行分页查询
     */
    List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds);

    /**
     * 
     * 
     * 根据主键字符串进行查询，类中只有存在一个带有@Id注解的字段
     *
     * @param ids 如 "1,2,3,4"
     */
    List<T> selectByIds(String ids);

    /**
     * 
     * 
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     */
    T selectByPrimaryKey(Object key);

    /**
     * 
     * 
     * 根据实体中的属性查询总数，查询条件使用等号
     */
    int selectCount(T model);


    /**
     * 
     * 
     * 根据Example条件进行查询总数
     */
    int selectCountByExample(Object example);

    /**
     * 
     * 
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     */
    T selectOne(T model);

    /**
     * 
     * 
     * 根据实体属性和RowBounds进行分页查询
     */
    List<T> selectByRowBounds(T model, RowBounds rowBounds);
    /**
     * 逻辑删除单条数据
     *
     */
    int deleteLogic(BaseLogicDel model);
    /**
     * 逻辑删除多条数据
     *
     */
    int deleteLogicList(String ids); 
    /**
     * 
     * 
     * 单表分页查询
     */
    //PageInfo selectPage(int pageNum, int pageSize, T model);

	int deleteLogicList(String[] ids);

	//List<T> selectExt(T t);

    /**
     * 
     * 
     * 根据Example条件进行分页查询
     */
    //PageInfo selectPageByExample(int pageNum, int pageSize, Object example);

}
