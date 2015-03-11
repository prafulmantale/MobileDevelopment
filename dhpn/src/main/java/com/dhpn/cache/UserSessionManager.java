package com.dhpn.cache;

import com.dhpn.model.User;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by prafulmantale on 3/4/15.
 */
public class UserSessionManager {

    private static UserSessionManager INSTANCE = new UserSessionManager();

    private ConcurrentMap<User, String> sessionMap;

    private UserSessionManager() {
        sessionMap = new ConcurrentHashMap<User, String>();
    }


    public static UserSessionManager getInstance(){

        return INSTANCE;
    }

    public void addUserSession(User user, String sessionId){

        sessionMap.put(user, sessionId);
    }
}
