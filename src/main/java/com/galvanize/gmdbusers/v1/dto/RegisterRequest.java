package com.galvanize.gmdbusers.v1.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegisterRequest {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
