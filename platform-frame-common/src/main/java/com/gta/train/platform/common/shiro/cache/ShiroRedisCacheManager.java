/******************************************************************************
 * Copyright (c) 2017.5.5 by JoyLau.                                          *
 * Copyright © 2017 Shenzhen GTA Education Tech Ltd. All rights reserved      *
 ******************************************************************************/

package com.gta.train.platform.common.shiro.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

 
/**
 * Created by ran.li on 8/9/2017.
 * com.gtafe.shiro
 * ran.li@gtafe.com
 */
@Component
public class ShiroRedisCacheManager extends AbstractCacheManager {
   @Autowired
    @Qualifier("shiroRedisTemplate")  
    private RedisTemplate<byte[], Object>  shiroRedisTemplate;

    public ShiroRedisCacheManager(RedisTemplate<byte[], Object>  shiroRedisTemplate) {
        this.shiroRedisTemplate = shiroRedisTemplate;
    }

    @Override
    protected Cache createCache(String name) throws CacheException {
        return new ShiroRedisCache (shiroRedisTemplate, name);
    }
  /*  @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        Cache cache = caches.get(name);
        if(cache == null){
            cache = new ShiroRedisCache (shiroRedisTemplate, name);
            caches.put(name,cache);
        }

        return cache;
    }
 
   
    @Override
	protected  Cache  createCache(String name) throws CacheException {
		// TODO 自动生成的方法存根
		return new ShiroRedisCache (shiroRedisTemplate, name);
	}
    
    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();
    //cache
 
    */
 

 /*   @Override
    protected <K, V> Cache<K, V>   createCache(String name) throws CacheException {
        return new ShiroRedisCache<K, V>(shiroRedisTemplate, name);
    }*/
    
  /*  @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new ShiroCache<K, V>(name, shiroRedisTemplate);
    }*/
	
}
