package com.gta.train.platform.common.redis;
 
import java.util.concurrent.Callable;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
 

import com.gta.train.platform.common.util.SpringContextHolder;

import net.sf.ehcache.Element;
import com.gta.train.platform.common.util.StringUtils;
public class EhRedisCacheFactory implements Cache {
	
	private boolean useRedis = true;
	private String cacheName;
	private net.sf.ehcache.Cache ehCache = null;
	
	public boolean isUseRedis() {
		return useRedis;
	}

	public void setUseRedis(boolean useRedis) {
		this.useRedis = useRedis;
	}

	@Override
	public String getName() {
		return this.cacheName;
	}

	@Override
	public Object getNativeCache() {
		return this;
	}

	
	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.ehCache = SpringContextHolder.getBean(cacheName);
		this.cacheName = cacheName;
	}

	/**
	 * @description 根据键获得键对应的json字符串类型的值信息
	 * @author fengya
	 * @date 2018年6月1日 下午5:25:25
	 * @param key
	 * @return
	 * @return String
	 */
	@Override
	public ValueWrapper get(Object key) {
		if (null == key) {
			return null;
		}
    	Element value = ehCache.get(key);
    	if (value != null) { 
    		//LOG.info("Cache L1 (ehcache) :{"+this.getName()+"}{"+key+"}={"+value.getObjectValue().toString()+"}");
    		return (value != null ? new SimpleValueWrapper(value.getObjectValue().toString()) : null);
    	}
    	if (this.useRedis) {
    		String redisValue = JedisClusterUtil.get(key.toString());
        	if (null != redisValue) {
        		ehCache.put(new Element(key, redisValue));
            	//LOG.info("Cache L2 (redis) :{"+this.getName()+"}{"+key+"}={"+redisValue+"}");
            	return new SimpleValueWrapper(redisValue);
        	}
    	}
    	return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object key, Class<T> type) {
		if (StringUtils.isEmpty(key) || null == type) {  
			return null;  
		} else { 
			final Object object = this.get(key);  
			if (type != null && type.isInstance(object) && null != object) {  
				return (T) object;  
			} else {  
				return null;  
			}  
		}
	}
	
	public String get(String key) {
		if (StringUtils.isEmpty(key)) {  
			return null;  
		} else { 
			final ValueWrapper valueWrapper = this.get((Object)key);  
			if (null != valueWrapper) {  
				return valueWrapper.get().toString();  
			} else {  
				return null;  
			}  
		}
	}

 
	public <T> T get(Object key, Callable<T> valueLoader) {
		return null;
	}
	
	/**
	 * @description 判断缓存中是否存在键key的数据
	 * @author fengya
	 * @date 2018年6月4日 上午11:13:46
	 * @param key
	 * @return
	 * @return boolean
	 */
	public boolean exists(String key) {
		if (StringUtils.isEmpty(key)) {  
			return false;  
		} else { 
			final ValueWrapper valueWrapper = this.get((Object)key);  
			if (null != valueWrapper) {  
				return true;  
			} else {  
				return false;  
			}  
		}
	}

	@Override
	public void put(Object key, Object value) {
		ehCache.put(new Element(key, value));
		if (this.useRedis) {
			JedisClusterUtil.set(key.toString(), value);
		}
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {  
			return null;  
	    } else {  
	    	this.put(key, value);  
	    }  
	    return new SimpleValueWrapper(value); 
	}

	/**
	 * @description 删除指定的对象缓存数据
	 * @author fengya
	 * @date 2018年6月1日 上午11:01:29
	 */
	@Override
	public void evict(Object key) {
		ehCache.remove(key); 
		if (this.useRedis) {
			JedisClusterUtil.delObject(key.toString());
		}
	}
	
	/**
	 * @description 删除指定的json字符串缓存数据
	 * @author fengya
	 * @date 2018年6月1日 下午4:59:51
	 * @param key
	 * @return void
	 */
	public void evictString(Object key) {
		ehCache.remove(key); 
		if (this.useRedis) {
			JedisClusterUtil.del(key.toString());
		}
	}

	@Override
	public void clear() {
		ehCache.removeAll(); 
	}
	
	/**
	 * @description 针对于用户的缓存存取的时间进行设置的方法，该设置时间对redis有效，对ehcache缓存依然依赖于配置信息有效时间，所以在设置seconds的数据时，一定要大于ehcache的有效时间。
	 * @author fengya
	 * @date 2018年6月4日 上午11:01:19
	 * @param key
	 * @param value
	 * @param seconds
	 * @return void
	 */
	public void put(Object key, Object value, long seconds) {
		ehCache.put(new Element(key, value));
		if (this.useRedis) {
			JedisClusterUtil.set(key.toString(), value.toString(),seconds);
		}
	}
}
