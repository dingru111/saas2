/******************************************************************************
 * Copyright (c) 2017.5.5 by JoyLau.                                          *
 * Copyright © 2017 Shenzhen GTA Education Tech Ltd. All rights reserved      *
 ******************************************************************************/

package com.gta.train.platform.common.shiro.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.gta.train.platform.common.util.SerializeUtils;
import com.gta.train.platform.common.util.SpringContextUtil;



/**
 * 
 * @author ran.li
 *
 * @param <K>
 * @param <V>
 */
@Component
public class ShiroRedisCache<K, V> implements Cache<K, V> {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    @Qualifier("shiroRedisTemplate") 
    private RedisTemplate<byte[], V> shiroRedisTemplate ;
    //  //private static RedisTemplate<String, Object> redisTemplate = SpringContextUtil.getBean("redisTemplate");

    private String prefix;

    public ShiroRedisCache() {
    }

    public ShiroRedisCache(RedisTemplate<byte[], V> redisTemplate) {
        this.shiroRedisTemplate = redisTemplate;
    }

    public ShiroRedisCache(RedisTemplate<byte[], V> redisTemplate, String prefix) {
        this(redisTemplate);
        this.prefix = prefix;
    }

    @Override
    public V get(K key) throws CacheException {
        if(log.isDebugEnabled()) {
            log.debug("Get Key: {}", key);
        }
        if(key == null) {
            return null;
        }
       byte[] bkey = getByteKey(key);
       Object object=shiroRedisTemplate.opsForValue().get(bkey);
       log.debug("shiroRedisTemplate  "+key+" 读取个人信息"+object );
        return (V) object;
    }

    @Override
    public V put(K key, V value) throws CacheException {
        if(log.isDebugEnabled()) {
            log.debug("Put Key: {}, value: {}", key, value);
        }

        if(key == null || value == null) {
            return null;
        }

        byte[] bkey = getByteKey(key);
        shiroRedisTemplate.opsForValue().set(bkey, value);
        log.debug("shiroRedisTemplate 写入个人信息"+key );
        return value;
    }

    @Override
    public V remove(K key) throws CacheException {
        if(log.isDebugEnabled()) {
            log.debug("Remove Key: {}", key);
        }

        if(key == null) {
            return null;
        }

        byte[] bkey = getByteKey(key);
        ValueOperations<byte[], V> vo = shiroRedisTemplate.opsForValue();
        V value = vo.get(bkey);
        shiroRedisTemplate.delete(bkey);
        return value;
    }

    @Override
    public void clear() throws CacheException {
        shiroRedisTemplate.getConnectionFactory().getConnection().flushDb();
    }

    @Override
    public int size() {
        Long len = shiroRedisTemplate.getConnectionFactory().getConnection().dbSize();
        return len.intValue();
    }

    @Override
    public Set<K> keys() {
        byte[] bkey = ("*"+prefix+"*").getBytes();
        Object o =shiroRedisTemplate.execute((RedisCallback) connection -> connection.keys(bkey));
        return o instanceof Set ? (Set<K>)o : null;
    }

    @Override
    public Collection<V> values() {
        Set<K> keys = keys();
        List<V> values = new ArrayList<>();
        for(K k: keys) {
            Object o = shiroRedisTemplate.execute((RedisCallback) connection -> connection.get((byte[]) k));
            V v= (V) SerializeUtils.deserialize((byte[]) o);
            values.add(v);
        }
        return  values;
    }

    private byte[] getByteKey(K key){
        if(key instanceof String){
            String preKey = this.prefix + key;
            return preKey.getBytes();
        }else{
            return SerializeUtils.serialize(key);
        }
    }

    public String getPrefix() {
        return prefix;
    }
}
