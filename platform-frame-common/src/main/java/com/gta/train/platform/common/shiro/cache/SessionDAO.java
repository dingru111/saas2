/******************************************************************************
 * Copyright (c) 2017.4.13 by JoyLau.                                         *
 * Copyright © 2017 Shenzhen GTA Education Tech Ltd. All rights reserved      *
 ******************************************************************************/

package com.gta.train.platform.common.shiro.cache;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.stereotype.Service;

/**
 * 
 * @author ran.li
 *
 */
/*@Service(value="customSessionDAO")*/

public class SessionDAO extends CachingSessionDAO {

    public SessionDAO() {
    }

    @Override
    protected void doUpdate(Session session) {
    	//System.out.println(session.getId());
    }

    @Override
    protected void doDelete(Session session) {}

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return getCache().get(sessionId);
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Cache<Serializable, Session> cache = getCache();
        if (cache != null) {
            return cache.values();
        } else {
            return Collections.emptySet();
        }
    }

    public Cache<Serializable, Session> getCache() {
        return getCacheManager().getCache(getActiveSessionsCacheName());
    }

    public Session getSession(Serializable sessionId) {
        return getCache().get(sessionId);
    }

    public void clearSession(Serializable sessionId){
        getCache().remove(sessionId);
    }
}
