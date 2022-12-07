package com.example.singupandin.security;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(SecurityConstants.HEADER_STRING);
       if(header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX) ){
           chain.doFilter(request,response);
           return;
       }
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){

        String token = request.getHeader(SecurityConstants.HEADER_STRING);

        if(token != null){

            token = token.replace(SecurityConstants.TOKEN_PREFIX,"");

            String user = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET)
                    .parseClaimsJws(token).getBody().getSubject();

            if(user != null){
                return  new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>());
            }
        return  null;
        }
        return null;
    }
}
