package com.example.singupandin.controller;

import com.example.singupandin.doa.UserRepository;
import com.example.singupandin.io.entities.EmailDetails;
import com.example.singupandin.io.entities.UserEntity;
import com.example.singupandin.service.UserService;
import com.example.singupandin.service.UserServiceImp;
import com.example.singupandin.service.emailService.EmailServiceImpl;
import com.example.singupandin.service.emailService.EmailServiceInterface;
import com.example.singupandin.share.dto.UserDtr;
import com.example.singupandin.ui.model.request.UserModelRequest;
import com.example.singupandin.ui.model.response.UserModelResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailServiceImpl emailService;

    private JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();



    @GetMapping
    public String getUser(){

        return "User was got";
    }

    @PostMapping("/emailSender")
    public ResponseEntity sendEmail(@RequestBody EmailDetails emailDetails){

     emailService.sendSimpleMail(emailDetails);
        return ResponseEntity.ok("Success");
    }


    @PostMapping
    public UserModelResponse createUser(@Valid  @RequestBody  UserModelRequest userModelRequest){

        if ( userRepository.findByEmail(userModelRequest.getEmail()) != null) throw new RuntimeException(" Record already exists");
        UserModelResponse userModelResponse = new UserModelResponse();
        UserDtr userDtr = new UserDtr();

        BeanUtils.copyProperties(userModelRequest,userDtr);

        UserDtr userCreate = userService.createUser(userDtr);
        BeanUtils.copyProperties(userCreate,userModelResponse);

       EmailDetails emailDetails = new EmailDetails("amine.elkahiri@usmba.ac.ma","Hi","welcome");
        emailService.sendSimpleMail(emailDetails);

        return userModelResponse;
    }

    private void sendEmail(){
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo("amine.elkahiri@usmba.ac.ma");
            helper.setText("Welcome to new generation");
            helper.setSubject("Hi");
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @PutMapping
    public String userUpdate(){
        return "User was updated";
    }

    @DeleteMapping
    public String deleteUser(){
        return "User was deleted";
    }

}
