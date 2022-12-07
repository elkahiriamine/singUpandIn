package com.example.singupandin.security;

public interface SecurityConstants {

    long EXPIRATION_TIME = 864000000;
    String TOKEN_PREFIX  = "Bearer ";
    String HEADER_STRING = "Authorization";
    String SIGN_UP_URL   = "/users";
    String TOKEN_SECRET  = "jf9i4jgu83nfl0";
}
