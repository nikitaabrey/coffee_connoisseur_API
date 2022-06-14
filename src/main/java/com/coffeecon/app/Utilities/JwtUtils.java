package com.coffeecon.app.Utilities;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class JwtUtils {

//    JwtAuthenticationToken auth = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

    public static String getUser(JwtAuthenticationToken token) {
        return (String) token.getTokenAttributes().get("username");
    }

}
