package com.example.singupandin.ui.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class UserModelRequest {


    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
