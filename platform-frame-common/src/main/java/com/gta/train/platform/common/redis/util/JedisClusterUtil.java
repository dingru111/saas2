package com.gta.train.platform.common.redis.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.gta.train.platform.common.util.SpringContextHolder;

import com.gta.train.platform.common.util.StringUtils;

public class JedisClusterUtil {
	private static StringRedisTemplate stringRedisTemplate = SpringContextHolder.getBean("stringRedisTemplate");
	private static RedisTemplate<String, Object> redisTemplate = SpringContextHolder.getBean("redisTemplate");

	private JedisClusterUtil() {
	}

	/**
	 * @description 根据键值取redis缓存中的数据
	 * @author fengya
	 * @date 2017年6月27日 下午4:02:07
	 * @param key
	 * @return
	 * @return String
	 */
	public static final String get(String key) {
		if (StringUtils.isNullOrEmpty(key)) {
			return null;
		}
		return stringRedisTemplate.opsForValue().get(key);
	}
	
	/**
	 * @description 根据键值从redis中获取对象
	 * @author fengya
	 * @date 2018年6月1日 上午10:52:23
	 * @param key
	 * @return
	 * @return Object
	 */
	public static final Object getObject(String key) {
		if (StringUtils.isNullOrEmpty(key)) {
			return null;
		}
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * @description 往redis服务器中，写数据
	 * @author fengya
	 * @date 2017年6月27日 下午4:04:12
	 * @param key
	 * @param value
	 * @return void
	 */
	public static final void set(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}
	
	/**
	 * @description 往redis中设置对象，对象必须进行序列化
	 * @author fengya
	 * @date 2018年6月1日 上午10:28:41
	 * @param key
	 * @param value
	 * @return void
	 */
	public static final void set(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * @description 对缓存中的键key进行设置有效期限
	 * @author fengya
	 * @date 2017年6月29日 下午4:00:01
	 * @param key
	 * @param seconds
	 *            必须大于0
	 * @return void
	 */
	public static final void expire(String key, long seconds) {
		if (seconds > 0) {
			stringRedisTemplate.expire(key, seconds, TimeUnit.SECONDS);
		}
	}

	/**
	 * @description 写缓存数据，并规定过期时间
	 * @author fengya
	 * @date 2017年6月27日 下午4:07:02
	 * @param key
	 * @param value
	 * @param seconds
	 *            过期时间，单位：秒
	 * @return void
	 */
	public static final void set(String key, String value, long seconds) {
		stringRedisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
	}
	
	/**
	 * @description 判断redis缓存中是否存在键为key的数据
	 * @author fengya
	 * @date 2017年6月29日 下午3:56:32
	 * @param key
	 * @return
	 * @return boolean
	 */
	public static final boolean exists(String key) {
		String value = get(key);
		if (StringUtils.isNullOrEmpty(value)) {
			return false;
		}
		return true;
	}

	/**
	 * @description 删除一个key
	 * @author fengya
	 * @date 2017年6月27日 下午5:02:56
	 * @param key
	 * @return void
	 */
	public static final void del(String... keys) {
		for (String key : keys) {
			stringRedisTemplate.delete(key);
		}
	}
	
	/**
	 * @description 从redis缓存中，删除对象
	 * @author fengya
	 * @date 2018年6月1日 上午11:09:08
	 * @param keys
	 * @return void
	 */
	public static final void delObject(String... keys) {
		for (String key : keys) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * @description 往缓存中添加一个list数据
	 * @author fengya
	 * @date 2017年8月10日 下午2:10:24
	 * @param key
	 * @param dataList
	 * @return
	 * @return ListOperations<String,Object>
	 */
	public static final ListOperations<String, Object> setCacheList(String key, List<Object> dataList) {
		ListOperations<String, Object> listOperation = redisTemplate.opsForList();
		if (null != dataList) {
			listOperation.rightPushAll(key, dataList);
		}
		return listOperation;
	}

	/**
	 * @description 读取缓存集合中所有的数据信息
	 * @author fengya
	 * @date 2017年8月10日 下午4:10:37
	 * @param key
	 * @return
	 * @return List<Object>
	 */
	public static final List<Object> getCacheList(String key) {
		return redisTemplate.opsForList().range(key, 0, -1);
	}

	/**
	 * @description 根据键值获得该键对应的集合数据,并对所有的记录数据进行出栈
	 * @author fengya
	 * @date 2017年8月10日 下午2:18:02
	 * @param key
	 * @return
	 * @return List<Object>
	 */
	public static final List<Object> listPopAll(String key) {
		List<Object> dataList = new ArrayList<Object>();
		ListOperations<String, Object> listOperation = redisTemplate.opsForList();
		Long size = listOperation.size(key);
		for (int i = 0; i < size; i++) {
			dataList.add(listOperation.leftPop(key));
		}
		return dataList;
	}

	/**
	 * @description 对缓存集合中的元素按照下标位置进行出栈操作
	 * @author fengya
	 * @date 2017年8月10日 下午4:07:44
	 * @param key
	 * @param start
	 *            下标开始位置
	 * @param end
	 *            下标结束位置 ，-1代表最后一个元素下标
	 * @return
	 * @return List<Object>
	 */
	public static final List<Object> listPopRange(String key, int start, long end) {
		List<Object> dataList = new ArrayList<Object>();
		ListOperations<String, Object> listOperation = redisTemplate.opsForList();
		long size = listOperation.size(key);
		end = end > size ? size : end;
		for (int i = start; i < end; i++) {
			dataList.add(listOperation.leftPop(key));
		}
		return dataList;
	}

	/**
	 * @description 获得缓存键值为Key集合中的下标范围内的集合数据
	 * @author fengya
	 * @date 2017年8月10日 下午2:19:26
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @return List<Object>
	 */
	public static final List<Object> range(String key, long start, long end) {
		ListOperations<String, Object> listOperation = redisTemplate.opsForList();
		return listOperation.range(key, start, end);
	}

	/**
	 * @description 获得缓存中集合的长度
	 * @author fengya
	 * @date 2017年8月10日 下午2:21:51
	 * @param key
	 * @return
	 * @return Long
	 */
	public static final Long listSize(String key) {
		return redisTemplate.opsForList().size(key);
	}

	/**
	 * @description 在集合指定下标内进行数据覆盖
	 * @author fengya
	 * @date 2017年8月10日 下午2:22:58
	 * @param key
	 * @param index
	 * @param obj
	 * @return void
	 */
	public static final void listSet(String key, int index, Object obj) {
		redisTemplate.opsForList().set(key, index, obj);
	}

	/**
	 * @description 向集合缓存的头部插入记录
	 * @author fengya
	 * @date 2017年8月10日 下午2:24:10
	 * @param key
	 * @param obj
	 * @return
	 * @return long
	 */
	public static final long leftPush(String key, Object obj) {
		return redisTemplate.opsForList().leftPush(key, obj);
	}

	/**
	 * @description 向集合缓存的尾部插入记录
	 * @author fengya
	 * @date 2017年8月10日 下午2:25:31
	 * @param key
	 * @param obj
	 * @return
	 * @return long
	 */
	public static final long rightPush(String key, Object obj) {
		return redisTemplate.opsForList().rightPush(key, obj);
	}

	/**
	 * @description 缓存集合中的数据，只保留start到end位置的元素
	 * @author fengya
	 * @date 2017年8月10日 下午2:27:05
	 * @param key
	 * @param start
	 *            记录的开始位置(0表示第一条记录)
	 * @param end
	 *            记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
	 * @return void
	 */
	public static final void trim(String key, int start, int end) {
		redisTemplate.opsForList().trim(key, start, end);
	}

	/**
	 * @description 删除缓存集合中指定位置的元素
	 * @author fengya
	 * @date 2017年8月10日 下午2:28:55
	 * @param key
	 * @param i
	 *            要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
	 * @param obj
	 *            要匹配的值
	 * @return
	 * @return long 删除后的List中的记录数
	 */
	public static final long remove(String key, long i, Object obj) {
		return redisTemplate.opsForList().remove(key, i, obj);
	}

	/**
	 * @description 在缓存中存储set集合，
	 * @author fengya
	 * @date 2017年8月10日 下午4:53:06
	 * @param key
	 * @param dataSet
	 * @return
	 * @return void
	 */
	public static final void setCacheSet(String key, Set<Object> dataSet) {
		redisTemplate.opsForSet().add(key, dataSet.toArray());
	}
	
	/**
	 * @description 得到集合中所有的元素
	 * @author fengya
	 * @date 2017年8月10日 下午5:10:35
	 * @param key
	 * @return
	 * @return Set<Object>
	 */
	public static final Set<Object> getCacheSet(String key) {
		return redisTemplate.opsForSet().members(key);
	}
	
	/**
	 * @description 对缓存中的集合进行出栈操作
	 * @author fengya
	 * @date 2017年8月10日 下午5:13:29
	 * @param key
	 * @return
	 * @return Object
	 */
	public static final Object setPop(String key) {
		return redisTemplate.opsForSet().pop(key);
	}
	
	/**
	 * @description 对缓存中的集合元素进行删除操作
	 * @author fengya
	 * @date 2017年8月10日 下午5:14:36
	 * @param key
	 * @param values 待删除的元素集合
	 * @return
	 * @return long 删除元素的个数
	 */
	public static final long setPop(String key,Object...values) {
		return redisTemplate.opsForSet().remove(key, values);
	}
	
	/**
	 * @description 往缓存写入map数据
	 * @author fengya
	 * @date 2017年8月10日 下午5:17:48
	 * @param key
	 * @param dataMap
	 * @return void
	 */
	public static final void setCacheMap(String key,Map<String, Object> dataMap) {
		redisTemplate.opsForHash().putAll(key, dataMap);
	}
	
	/**
	 * @description 根据键值从缓存中取map数据
	 * @author fengya
	 * @date 2017年8月10日 下午5:20:40
	 * @param key
	 * @return
	 * @return Map<Object,Object>
	 */
	public static final Map<Object, Object> getCacheMap(String key) {
		return redisTemplate.opsForHash().entries(key);
	}
	
	/**
	 * @description 从缓存中取根据Map中的键取值对象
	 * @author fengya
	 * @date 2017年8月10日 下午5:23:11
	 * @param key
	 * @param mapKey
	 * @return
	 * @return Object
	 */
	public static final Object getMapValue(String key,String mapKey) {
		return redisTemplate.opsForHash().get(key, mapKey);
	}
	
	/**
	 * @description 往缓存Map中，添加一组元素
	 * @author fengya
	 * @date 2017年8月10日 下午5:25:11
	 * @param key 缓存中对应的键
	 * @param mapKey map中的键
	 * @param mapValue map中的值
	 * @return void
	 */
	public static final void mapPush(String key,String mapKey,Object mapValue) {
		redisTemplate.opsForHash().put(key, mapKey, mapValue);
	}
	
	/**
	 * @description 从缓存中对应键的key中的map中，根据map中的键进行删除该键对应的元素
	 * @author fengya
	 * @date 2017年8月10日 下午5:29:53
	 * @param key 缓存中的键
	 * @param mapKey map中的键
	 * @return void
	 */
	public static final void deleteMapByMapKey(String key,String mapKey) {
		redisTemplate.opsForHash().delete(key, mapKey);
	}
}
