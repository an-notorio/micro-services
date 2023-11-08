package com.example.authenticator.dto;

import com.example.authenticator.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private List<Role> role;
}