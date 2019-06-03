package com.gta.train.platform.common.redis.constant;
/**
 * @Title:
 * @Description: redis全局配置常量
 * @Copyright: Copyright (c) 2017
 * @Company: www.gtafe.com
 * @author fengya
 * @version 1.0
 * @date  2017年6月29日 下午2:12:02
 */
public class RedisConstant {
	private RedisConstant(){}
//	/**
//	 * 用户登陆系统后，登陆时长在缓存中存放的过期时长
//	 */
//	public static long USER_LOGIN_TIME_KEY_LONG = 180;
	/**
	 * 用户登陆时效长度,ticket的有效期
	 */
	public static long USER_LOGIN_KEY_LONG = 1800;
}
