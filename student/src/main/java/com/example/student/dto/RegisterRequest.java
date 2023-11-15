package com.example.student.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotNull(message = "cannot be blank")
    private String firstname;
    @NotNull(message = "cannot be blank")
    private String lastname;
    @Email(message = "invalid email address")
    private String email;
    @NotNull(message = "cannot be blank")
    private Integer schoolId;
}

