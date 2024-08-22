package com.payroll.auth.service.controller;


import com.payroll.auth.service.dto.LoginDto;
import com.payroll.auth.service.dto.LoginResponseDto;
import com.payroll.auth.service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.function.LongFunction;
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto)
    {
        LoginResponseDto loginResponseDto=authService.login(loginDto);
        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }


    @PostMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token) {
        boolean isValid = authService.validate(token);

        if (isValid) {
            return ResponseEntity.ok("Token is valid.");
        } else {
            return ResponseEntity.status(401).body("Invalid or expired token.");
        }
    }


}
