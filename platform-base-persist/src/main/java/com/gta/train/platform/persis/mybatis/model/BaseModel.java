/*******************************************************************************
 * Copyright (c) 2016.10.31 by LiuFa.
 * Copyright © 2016 Shenzhen GTA Education Tech Ltd. All rights reserved
 ******************************************************************************/

package com.gta.train.platform.persis.mybatis.model;


import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

 


/**
 * Created by JoyLau on 4/05/2017.
 * com.gtafe.base.model
 * fa.liu@gtafe.com
 * 每个实体都有的id属性，另外增加了createTime和updateTime，虽然在业务上可能没有什么用处，但是对于开发和运维的作用相当大，谁用谁知道
 */
public class BaseModel extends FunctionModel {// 
 	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	@Transient
 	public Map map=new HashMap();
 
	@SuppressWarnings("rawtypes")
	public Map getMap() {
		return map;
	}
	@SuppressWarnings("rawtypes")
	public void setMap(Map map) {
		this.map = map;
	}

	 
	
}
