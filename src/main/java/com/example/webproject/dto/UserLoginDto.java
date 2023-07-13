package com.example.webproject.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginDto implements Serializable {
    private String email;
    private String password;
    private Boolean returnSecureToken;
}
