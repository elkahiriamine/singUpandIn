package com.example.singupandin.service;

import com.example.singupandin.doa.UserRepository;
import com.example.singupandin.io.entities.UserEntity;
import com.example.singupandin.share.dto.UserDtr;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDtr createUser(UserDtr userDtr) {

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDtr,userEntity);

        userEntity.setUserID(Utils.generateUserId(30));
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDtr.getPassword()));
        userEntity.setEmailVerificationStatus(true);


        UserEntity userCreated = userRepository.save(userEntity);

        UserDtr userRes =  new UserDtr();
        BeanUtils.copyProperties(userCreated,userRes);

        return userRes;
    }

    @Override
    public UserDtr getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if( userEntity == null) throw new UsernameNotFoundException(email);
        UserDtr userDtr =  new UserDtr();
        System.out.println(userEntity.getId());
        BeanUtils.copyProperties(userEntity,userDtr);
        return userDtr;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),new ArrayList<>());
    }
}
