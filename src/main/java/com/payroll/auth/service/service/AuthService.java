package com.payroll.auth.service.service;


import com.payroll.auth.service.dto.LoginDto;
import com.payroll.auth.service.dto.LoginResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginDto loginDto);
    Boolean validate(String token);
}
