/*******************************************************************************
 * Copyright (c) 2017 by JoyLau. All rights reserved
 ******************************************************************************/

package com.gta.train.platform.persis.mybatis.plugin;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.UpdateProvider;

import com.gta.train.platform.persis.mybatis.plugin.baseopt.interfaces.BaseLogicDel;
import com.gta.train.platform.persis.mybatis.plugin.ids.IdsMapper;
import com.gta.train.platform.persis.mybatis.plugin.ids.provider.DeleteLogicProvider;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by JoyLau on 4/6/2017.
 * cn.joylau.mybatis.mapper.function
 * 2587038142.liu@gmail.com
 * FunctionMapper集成了MySQL所使用的绝大部分Mapper了，也可以很好的进行扩展
 */
public interface FunctionMapper<T> extends Mapper<T>, InsertListMapper<T>,/*MySqlMapper<T>,*/IdsMapper<T> {
	/**
	 *  通过id逻辑删除
	 * */
	@UpdateProvider(type = DeleteLogicProvider.class, method = "dynamicSQL")
	@Options(useCache = false, useGeneratedKeys = false)
	public int deleteLogic(BaseLogicDel model);
	/**
 	 * **通过ids数组逻辑删除*/
 	@UpdateProvider(type = DeleteLogicProvider.class, method = "dynamicSQL")
	@Options(useCache = false, useGeneratedKeys = false)
	public int deleteLogicList(String[] ids); 
 	//@SelectKey(before = true, keyProperty = "id", resultType = String.class, statement = { "select replace(uuid(),'-','') uuid ;" })
 	//@Override
 	//public int insertList(List<T> list );
 
 	//public List<T> selectExt(T t);
}
