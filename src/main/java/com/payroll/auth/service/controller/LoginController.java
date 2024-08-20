package com.payroll.auth.service.controller;


import com.payroll.auth.service.dto.LoginDto;
import com.payroll.auth.service.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto)
    {
        String token=loginService.login(loginDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }


}
