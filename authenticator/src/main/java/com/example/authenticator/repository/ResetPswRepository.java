package com.example.authenticator.repository;

import com.example.authenticator.model.ResetPsw;
import com.example.authenticator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResetPswRepository extends JpaRepository<ResetPsw, Long> {
    ResetPsw findResetPswByResetToken(String token);

    List<ResetPsw> findAllByUser(User user);
}
