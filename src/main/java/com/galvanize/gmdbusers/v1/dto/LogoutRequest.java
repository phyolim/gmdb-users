package com.galvanize.gmdbusers.v1.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LogoutRequest {
    @NotEmpty
    private String email;
}
