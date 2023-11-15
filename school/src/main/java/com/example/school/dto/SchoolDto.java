package com.example.school.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SchoolDto {
    @NotNull(message = "cannot be blank")
    private String name;
    @NotNull(message = "cannot be blank")
    @Email(message = "invali email address")
    private String email;
}
