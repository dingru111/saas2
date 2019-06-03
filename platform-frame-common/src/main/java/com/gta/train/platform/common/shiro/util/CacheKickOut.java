/******************************************************************************
 * Copyright (c) 2017.5.23 by JoyLau.                                         *
 * Copyright © 2017 Shenzhen GTA Education Tech Ltd. All rights reserved      *
 ******************************************************************************/

package com.gta.train.platform.common.shiro.util;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gta.train.platform.common.shiro.SysTemShiroRealm;
import com.gta.train.platform.common.shiro.cache.SessionDAO;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by JoyLau on 5/23/2017.
 * com.gtafe.shiro
 * fa.liu@gtafe.com
 */
public class CacheKickOut {

    private static final String ACTIVE_KICK_OUT_CACHE_NAME = "shiro-activeKickOut";

    private static final String DEQUE_CACHE_KEY = "KickOut-DequeCacheKey-";

    private CacheManager cacheManager;

    private Cache<String, Deque<Serializable>> activeDeque;

    private String activeDequeCacheName;

    private String dequeCacheKey;

	 @Autowired
	 @Qualifier(value="customSessionDAO")
	 private SessionDAO customSessionDAO;

    @Autowired
    private SysTemShiroRealm authorizingRealm;

    public CacheKickOut(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }


    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public static String getActiveKickOutCacheName() {
        return ACTIVE_KICK_OUT_CACHE_NAME;
    }

    public String getDequeCacheKey() {
        return DEQUE_CACHE_KEY;
    }


    private Cache<String, Deque<Serializable>> createActiveSessionsCache() {
        Cache<String, Deque<Serializable>> cache = null;
        CacheManager mgr = getCacheManager();
        if (mgr != null) {
            String name = getActiveKickOutCacheName();
            cache = mgr.getCache(name);
        }
        return cache;
    }

    public Cache<String, Deque<Serializable>> getCache(){
        return createActiveSessionsCache();
    }

    Deque<Serializable> getDeque(String key){
        Deque deque = getCache().get(getDequeCacheKey() + key);
        return null == deque ? new ArrayDeque<>() : deque;
    }

    void putDeque(String key, Deque<Serializable> deque){
        getCache().put(getDequeCacheKey() + key, deque);
    }


   public void offLineUser(String key){
	   ;
	  // AuthorizingRealm authorizingRealm=(AuthorizingRealm)authorizingRealm;
       /* authorizingRealm.clearAuthenticationCache(key);*/
        Deque<Serializable> deque = getDeque(key);
        for (Serializable sessionId : deque) {
        	customSessionDAO.clearSession(sessionId);
        }
   }
}
