package com.example.singupandin.ui.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class UserModelResponse {

    private String UserID;

    private String firstName;

    private String lastName;

    private String email;
}
