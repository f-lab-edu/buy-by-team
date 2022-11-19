package com.flab.bbt.common;

import com.flab.bbt.auth.service.PasswordEncrypter;
import com.flab.bbt.user.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
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

    public void expire(HttpServletRequest request){
        Cookie sessionCookie = findCookie(request, COOKIE_SESSION_ID);
        if(sessionCookie!=null){
            sessionStore.remove(sessionCookie.getValue());
        }
    }

    public Cookie findCookie(HttpServletRequest request, String cookieName){
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny().orElse(null);
    }
}
