package com.example.singupandin.service;

import com.example.singupandin.io.entities.UserEntity;
import com.example.singupandin.share.dto.UserDtr;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {


     UserDtr createUser(UserDtr userDtr);
     UserDtr getUser(String email);


}
