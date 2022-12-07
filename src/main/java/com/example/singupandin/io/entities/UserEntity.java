package com.example.singupandin.io.entities;

import jakarta.persistence.*;
import jdk.jfr.BooleanFlag;
import lombok.Data;
import org.hibernate.annotations.Columns;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity(name = "UserEntity")
public class UserEntity {
    private static final long serialVersionUID = 322555487L;

    @Id @GeneratedValue
    private long id;

    @NotBlank @Column(name = "UserID",unique = true)
    private String UserID;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email @Column(name = "email",unique = true)
    private String email;

    @NotNull
    private String encryptedPassword;
    private String emailVerificationToken;

    @BooleanFlag
    private boolean emailVerificationStatus = false;



}
