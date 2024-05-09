package com.example.authenticator.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResetPasswordDto {
    private String password;
    private String repeatPassword;

    public ResetPasswordDto() {
    }

    public ResetPasswordDto(String password, String repeatPassword) {
        this.password = password;
        this.repeatPassword = repeatPassword;
    }
}
