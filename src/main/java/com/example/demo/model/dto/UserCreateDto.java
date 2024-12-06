package com.example.demo.model.dto;

import lombok.Data;

@Data
public class UserCreateDto {
    private String username;

    private String email;

    private String password;

    private String tenantName;
}
