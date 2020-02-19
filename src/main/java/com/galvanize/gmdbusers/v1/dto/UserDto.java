package com.galvanize.gmdbusers.v1.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String password;

    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
