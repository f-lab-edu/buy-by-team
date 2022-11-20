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

public class SessionManager {
    public static final String COOKIE_SESSION_ID = "sessionId";
}
