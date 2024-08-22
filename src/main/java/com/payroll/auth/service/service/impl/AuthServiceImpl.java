package com.payroll.auth.service.service.impl;

import com.payroll.auth.service.dto.LoginDto;
import com.payroll.auth.service.dto.LoginResponseDto;
import com.payroll.auth.service.entity.EmployeeEntity;
import com.payroll.auth.service.repository.EmployeeRepository;
import com.payroll.auth.service.service.AuthService;
import com.payroll.auth.service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public LoginResponseDto login(LoginDto loginDto)
    {
        EmployeeEntity employee = employeeRepository.findByEmail(loginDto.getEmail());

        if (employee != null) {
            boolean isPasswordMatch =passwordEncoder.matches(loginDto.getPassword(), employee.getPassword());

            if (isPasswordMatch) {
                // Generate JWT token
                LoginResponseDto loginResponseDto= LoginResponseDto.builder()
                        .role(employee.getRole())
                        .token(jwtUtil.generateToken(employee.getEmail()))
                        .build();
                return loginResponseDto;
            }
        }

        return null;

    }

    @Override
    public Boolean validate(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        try {
            String username = jwtUtil.extractUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            return jwtUtil.validateToken(token, userDetails);
        } catch (Exception e) {
            // Log the exception or handle it accordingly
            return false;
        }
    }



}
