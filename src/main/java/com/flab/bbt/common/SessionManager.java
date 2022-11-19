package com.flab.bbt.common;

import com.flab.bbt.auth.service.PasswordEncrypter;
import com.flab.bbt.user.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {
    public static final String COOKIE_SESSION_ID = "sessionId";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    public void createSession(Object value, HttpServletResponse response){
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        Cookie sessionCookie = new Cookie(COOKIE_SESSION_ID, sessionId);
        response.addCookie(sessionCookie);
    }
}
