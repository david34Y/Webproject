package com.example.webproject.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UsersDto implements Serializable {
    private String accountType;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String uid;
}
