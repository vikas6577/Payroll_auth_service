package com.payroll.auth.service.service;


import com.payroll.auth.service.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
    Boolean validate(String token);
}
