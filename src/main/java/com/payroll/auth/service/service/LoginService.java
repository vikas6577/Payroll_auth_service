package com.payroll.auth.service.service;


import com.payroll.auth.service.dto.LoginDto;

public interface LoginService {
    String login(LoginDto loginDto);
}
