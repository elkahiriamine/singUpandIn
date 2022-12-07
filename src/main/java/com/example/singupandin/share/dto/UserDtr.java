package com.example.singupandin.share.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class UserDtr implements Serializable {
     private static final long serialVersionUID = 2333332333322333L;

     private long id;
     private String userID;
     private String firstName;
     private String lastName;
     private String email;
     private String password;
     private String encryptedPassword;
     private String emailVerificationToken;
     private Boolean emailVerificationStatus = true;


}
