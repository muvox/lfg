package com.a1704471.lookingforgamer.security;

public class SecurityConstants {
    public static final String SECRET = "VerySpecialSecretTokenPart";
    public static final long EXPIRATION_TIME = 864_000_000 ;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
}
