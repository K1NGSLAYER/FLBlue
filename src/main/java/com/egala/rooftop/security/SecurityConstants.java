package com.egala.rooftop.security;

import com.egala.rooftop.SpringApplicationContext;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 360000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/adduser";
    public static final String SIGN_IN_URL = "/users/signin";
   // public static final String TOKEN_SECRET = getTokenSecret();
    public static String getTokenSecret(){
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return appProperties.getTokenSecret();
    }
}
