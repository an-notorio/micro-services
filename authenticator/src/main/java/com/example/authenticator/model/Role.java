package com.example.authenticator.model;

import com.example.authenticator.common.RoleName;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;

    @Enumerated(EnumType.STRING)
    private RoleName role;


}
