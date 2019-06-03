package com.gta.train.platform.common.base.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.train.platform.common.base.service.FunctionService;
 
import com.gta.train.platform.persis.mybatis.plugin.FunctionMapper;
import com.gta.train.platform.persis.mybatis.plugin.baseopt.interfaces.BaseLogicDel;

/**
 * @description 通用业务实现
 * @author 王博海
 * @2018年11月5日
 */
 
public abstract class FunctionServiceImpl<T> implements FunctionService<T>{
	@Autowired
	private FunctionMapper<T>  functionMapper;


 

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     */
 
    public int insert(T model) {
        return functionMapper.insert(model);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     */
 
    public int insertSelective(T model) {
        return functionMapper.insertSelective(model);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 批量插入，支持批量插入的数据库可以使用，另外该接口限制实体包含`id`属性并且必须为自增列
     */
 
    public int insertList(List<T> list) {
        return functionMapper.insertList(list);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 根据主键更新实体全部字段，null值会被更新
     */
 
    public int updateByPrimaryKey(T model) {
        return functionMapper.updateByPrimaryKey(model);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 根据主键更新属性不为null的值
     */
 
    public int updateByPrimaryKeySelective(T model) {
        return functionMapper.updateByPrimaryKeySelective(model);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 根据Example条件更新实体`model`包含的全部属性，null值会被更新
     */
 
    public int updateByExample(T model, Object example) {
        return functionMapper.updateByExample(model, example);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 根据Example条件更新实体`model`包含的不是null的属性值
     */
 
    public int updateByExampleSelective(T model, Object example) {
        return functionMapper.updateByExampleSelective(model, example);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 根据实体属性作为条件进行删除，查询条件使用等号
     */
 
    public int delete(T model) {
        return functionMapper.delete(model);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 根据实体id删除
     */
 
    public int deleteById(int id) {
        return functionMapper.deleteByPrimaryKey(id);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 根据Example条件删除数据
     */
 
    public int deleteByExample(Object example) {
        return functionMapper.deleteByExample(example);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 根据主键字符串进行删除，类中只有存在一个带有@Id注解的字段
     *
     * @param ids 如 "1,2,3,4"
     */
 
    public int deleteByIds(String ids) {
        return functionMapper.deleteByIds(ids);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     */
 
    public int deleteByPrimaryKey(Object key) {
        return functionMapper.deleteByPrimaryKey(key);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 根据实体中的属性值进行查询，查询条件使用等号
     */
 
    public List<T> select(T model) {
        return functionMapper.select(model);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 根据实体中的id查询实体
     */
 
    public T selectById(Object id) {
        return selectByPrimaryKey(id);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 查询全部结果
     */
 
    public List<T> selectAll() {
        return functionMapper.selectAll();
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 根据Example条件进行查询
     */
 
    public List<T> selectByExample(Object example) {
        return functionMapper.selectByExample(example);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 根据example条件和RowBounds进行分页查询
     */
 
    public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {
        return functionMapper.selectByExampleAndRowBounds(example, rowBounds);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 根据主键字符串进行查询，类中只有存在一个带有@Id注解的字段
     *
     * @param ids 如 "1,2,3,4"
     */
 
    public List<T> selectByIds(String ids) {
        return functionMapper.selectByIds(ids);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     */
 
    public T selectByPrimaryKey(Object key) {
        return functionMapper.selectByPrimaryKey(key);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 根据实体中的属性查询总数，查询条件使用等号
     */
 
    public int selectCount(T model) {
        return functionMapper.selectCount(model);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 根据Example条件进行查询总数
     */
 
    public int selectCountByExample(Object example) {
        return functionMapper.selectCountByExample(example);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     */
 
    public T selectOne(T model) {
        return functionMapper.selectOne(model);
    }

    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 根据实体属性和RowBounds进行分页查询
     */
 
    public List<T> selectByRowBounds(T model, RowBounds rowBounds) {
        return functionMapper.selectByRowBounds(model, rowBounds);
    }
 /*
    public List<T> selectExt(T t){
    	return functionMapper.selectExt(t);
    }*/
    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 单表分页查询
     */
/* 
    public PageInfo selectPage(int pageNum, int pageSize, T model) {
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = select(model);
        return new PageInfo<>(list);
    }
*/
    /**
     * Created by JoyLau on 4/6/2017.
     * 2587038142.liu@gmail.com
     * 根据Example条件进行分页查询
     */
/* 
    public PageInfo selectPageByExample(int pageNum, int pageSize, Object example) {
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = selectByExample(example);
        return new PageInfo<>(list);
    }*/

	
	public int deleteLogic(BaseLogicDel model) {
		// TODO 自动生成的方法存根
		return functionMapper.deleteLogic(model);
	}

	
	public int deleteLogicList(String ids) {
		// TODO 自动生成的方法存根
		if (StringUtils.isBlank(ids)) {
			return 0;
		}
		return functionMapper.deleteLogicList(ids.split(","));
	}
	
	public int deleteLogicList(String[] ids) {
		// TODO 自动生成的方法存根
		return functionMapper.deleteLogicList(ids );
	}

}
