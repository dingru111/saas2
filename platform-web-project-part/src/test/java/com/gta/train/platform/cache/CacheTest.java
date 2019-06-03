package com.gta.train.platform.cache;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import com.gta.train.platform.BaseTest;
import com.gta.train.platform.common.util.JedisClusterUtil;
import com.gta.train.platform.common.util.SerializeUtils;

public class CacheTest extends BaseTest {
	 @Autowired
	    @Qualifier("shiroRedisTemplate")
	    private RedisTemplate  shiroRedisTemplate ;
	//@Test
	public void test() {
		JedisClusterUtil.set("lkjlkjjklasdf", "11111111111");
		System.out.println(JedisClusterUtil.get("lkjlkjjklasdf"));
	}

	@Test
	public void test1()  {

	    SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
	    byte[] bkey = getByteKey("c0233b04e18a11e8984cd4ae52b618b7");
	    shiroRedisTemplate.opsForValue().set(bkey,info);
		Object o=shiroRedisTemplate.opsForValue().get(bkey);
		System.out.println(o);
	}
	

    private byte[] getByteKey(Object key){
        if(key instanceof String){
            String preKey =  key.toString();
            return preKey.getBytes();
        }else{
            return SerializeUtils.serialize(key);
        }
    }
}
