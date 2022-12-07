package com.example.singupandin.security;

import com.example.singupandin.SpringApplicationContext;
import com.example.singupandin.service.UserService;
import com.example.singupandin.share.dto.UserDtr;
import com.example.singupandin.ui.model.request.UserLoginRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import io.jsonwebtoken.*;

import java.io.IOException;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
           try {

               UserLoginRequestModel creds = new ObjectMapper().readValue(request.getInputStream(),
                       UserLoginRequestModel.class);

               return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                       creds.getEmail(),creds.getPassword(),new ArrayList<>()
               ));
           }catch (IOException exception){
                  throw new RuntimeException(exception);
           }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
            String userName = ((User) authResult.getPrincipal()).getUsername();

            String token = Jwts.builder()
                    .setSubject(userName)
                    .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET)
                    .compact();

        UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImp");
         UserDtr userDtr  = userService.getUser(userName);

            response.setHeader(SecurityConstants.HEADER_STRING,SecurityConstants.TOKEN_PREFIX+token);
           response.setHeader("userID",userDtr.getUserID());
    }
}









