package com.gta.train.platform.config.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.gta.train.platform.common.util.SpringContextHolder;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class RedisClusterConfig extends CachingConfigurerSupport {

	private JedisPoolConfig poolConfig(int maxIdle, int maxTotal, long maxWaitMillis, boolean testOnBorrow,
			boolean testOnReturn) {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxTotal(maxTotal);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		jedisPoolConfig.setTestOnBorrow(testOnBorrow);
		jedisPoolConfig.setTestOnReturn(testOnReturn);
		return jedisPoolConfig;
	}

	private RedisClusterConfiguration redisCluterConfig(String clusterNodes, Long timeout, int redirects,
			String password) {
		Map<String, Object> source = new HashMap<String, Object>();
		source.put("spring.redis.cluster.nodes", clusterNodes);
		// source.put("spring.redis.cluster.timeout", timeout);
		// source.put("spring.redis.cluster.max-redirects", redirects);
		return new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
	}

	@Bean(name = "redisConnectionFactory")
	public JedisConnectionFactory redisConnectionFactory(@Value("${spring.redis.cluster.maxIdle}") int maxIdle,
			@Value("${spring.redis.cluster.maxTotal}") int maxTotal,
			@Value("${spring.redis.cluster.maxWaitMillis}") long maxWaitMillis,
			@Value("${spring.redis.cluster.testOnBorrow}") boolean testOnBorrow,
			@Value("${spring.redis.cluster.testOnReturn}") boolean testOnReturn,
			@Value("${spring.redis.cluster.nodes}") String clusterNodes,
			@Value("${spring.redis.cluster.timeout}") Long timeout,
			@Value("${spring.redis.cluster.max-redirects}") int redirects,
			@Value("${spring.redis.cluster.password}") String password) {
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory(
				redisCluterConfig(clusterNodes, timeout, redirects, password),
				poolConfig(maxIdle, maxTotal, maxWaitMillis, testOnBorrow, testOnReturn));
		connectionFactory.setPassword(password);
		return connectionFactory;
	}

	@Bean(name = "stringRedisTemplate")
	public StringRedisTemplate stringRedisTemplate() {
		StringRedisTemplate template = new StringRedisTemplate();
		JedisConnectionFactory jedisConnectionFactory = SpringContextHolder.getBean("redisConnectionFactory");
		template.setConnectionFactory(jedisConnectionFactory);
		return template;
	}

	@SuppressWarnings("rawtypes")
	@Bean(name = "redisTemplate")
	@DependsOn("redisConnectionFactory")
	public RedisTemplate redisTemplate(JedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
	//	JedisConnectionFactory jedisConnectionFactory = SpringContextHolder.getBean("redisConnectionFactory");
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new JdkSerializationRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new JdkSerializationRedisSerializer());
		return template;
	}
	
	@Bean(name = "shiroRedisConnectionFactory")
	public JedisConnectionFactory shiroRedisConnectionFactory(@Value("${spring.redis.cluster.maxIdle}") int maxIdle,
			@Value("${spring.redis.cluster.maxTotal}") int maxTotal,
			@Value("${spring.redis.cluster.maxWaitMillis}") long maxWaitMillis,
			@Value("${spring.redis.cluster.testOnBorrow}") boolean testOnBorrow,
			@Value("${spring.redis.cluster.testOnReturn}") boolean testOnReturn,
			@Value("${spring.redis.cluster.nodes}") String clusterNodes,
			@Value("${spring.redis.cluster.timeout}") Long timeout,
			@Value("${spring.redis.cluster.max-redirects}") int redirects,
			@Value("${spring.redis.cluster.password}") String password) {
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory(
				redisCluterConfig(clusterNodes, timeout, redirects, password),
				poolConfig(maxIdle, maxTotal, maxWaitMillis, testOnBorrow, testOnReturn));
		connectionFactory.setPassword(password);
		return connectionFactory;
	}
	
	
	
	/**
	 * @description 
	 * @author wbh
	 * @date 2018年11月12日 下午3:45:29
	 * @param shiroRedisConnectionFactory
	 * @return
	 * RedisTemplate
	 */
	@SuppressWarnings("rawtypes")
	@Bean(name = "shiroRedisTemplate")
	@DependsOn("shiroRedisConnectionFactory")
	public RedisTemplate shiroRedisTemplate(@Qualifier("shiroRedisConnectionFactory")JedisConnectionFactory shiroRedisConnectionFactory) {
		RedisTemplate<byte[], Object>  template = new RedisTemplate<byte[], Object>();
	//	JedisConnectionFactory jedisConnectionFactory = SpringContextHolder.getBean("redisConnectionFactory");
		template.setConnectionFactory(shiroRedisConnectionFactory);
		//template.setKeySerializer(new StringRedisSerializer());
		//template.setValueSerializer(  new RedisObjectSerializer());
		template.setDefaultSerializer(new JdkSerializationRedisSerializer());
		//template.setHashKeySerializer(new StringRedisSerializer());
		//template.setHashValueSerializer(new JdkSerializationRedisSerializer());
		return template;
	}
}
