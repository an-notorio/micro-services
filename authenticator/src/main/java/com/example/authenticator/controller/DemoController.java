package com.example.authenticator.controller;

import com.example.authenticator.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Demo")
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private AuthenticationService service;

    @GetMapping("/prova")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("@securityService.hasPermission(#request, #userId)")
    public ResponseEntity<?> getUser(@PathVariable Integer userId, HttpServletRequest request){
        return new ResponseEntity<>(service.getUser(userId), HttpStatus.OK);
    }
}
