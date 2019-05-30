package me.own.learn.authorization.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

    public static final String COOKIE_NAME_TOKEN = "learn.token";

    public static final String COOKIE_NAME_ADMIN_TOKEN = "learn.adminToken";

    public static void setCustomerTokenInCookie(HttpServletResponse response, String tokenValue) {
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, tokenValue);
        cookie.setPath("/");
        cookie.setMaxAge(3600 * 24 * 7);
        response.addCookie(cookie);
    }

    public static String getCustomerTokenInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (COOKIE_NAME_TOKEN.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void setAdminUserTokenInCookie(HttpServletResponse response, String tokenValue) {
        Cookie cookie = new Cookie(COOKIE_NAME_ADMIN_TOKEN, tokenValue);
        cookie.setPath("/");
        cookie.setMaxAge(3600 * 24 * 7);
        response.addCookie(cookie);
    }

    public static String getAdminUserTokenInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (COOKIE_NAME_ADMIN_TOKEN.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
