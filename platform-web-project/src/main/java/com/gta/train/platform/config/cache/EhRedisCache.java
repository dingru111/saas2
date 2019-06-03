package com.gta.train.platform.config.cache;

import com.gta.edu.sdk.redis.util.EhRedisCacheFactory;
import com.gta.edu.sdk.util.SpringContextHolder;

public enum EhRedisCache {
	NO_LIMIT_EH_REDIS_CACHE("noLimitEhRedisCache1"),
	USER_EH_CACHE("userEhCache1"),
	USER_EH_REDIS_CACHE("userEhRedisCache1");
	private String value;

	private EhRedisCache(String value) {
		this.value = value;
 
	}

	public EhRedisCacheFactory getCache() {
		return (EhRedisCacheFactory) SpringContextHolder.getBean(this.value);
	}
}
