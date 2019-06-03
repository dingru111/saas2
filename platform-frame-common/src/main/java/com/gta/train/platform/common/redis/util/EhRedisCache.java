package com.gta.train.platform.common.redis.util;

import com.gta.train.platform.common.util.SpringContextHolder;

/**
 * <p> Title: </p>
 * <p> Description: 进行缓存存取的有期限和无期限类的选择枚举类型的入口</p>
 * <p> Copyright: Copyright (c) 2017 </p>
 * <p> Company: www.gtafe.com </p>
 * @author fengya
 * @version 1.0
 * @date  2018年6月1日 下午4:55:28
 */
public enum EhRedisCache {
	NO_LIMIT_EH_REDIS_CACHE("noLimitEhRedisCache"),USER_EH_REDIS_CACHE("userEhRedisCache");
	
	private String value;
	
	private EhRedisCache(String value){
		this.value = value;
	}
	
	public EhRedisCacheFactory getCache(){
		return (EhRedisCacheFactory)SpringContextHolder.getBean(this.value);
	}
}
