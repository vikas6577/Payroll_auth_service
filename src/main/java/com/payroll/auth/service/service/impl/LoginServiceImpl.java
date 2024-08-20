package com.payroll.auth.service.service.impl;

import com.payroll.auth.service.dto.LoginDto;
import com.payroll.auth.service.entity.EmployeeEntity;
import com.payroll.auth.service.repository.EmployeeRepository;
import com.payroll.auth.service.service.LoginService;
import com.payroll.auth.service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public String login(LoginDto loginDto)
    {
        EmployeeEntity employee = employeeRepository.findByEmail(loginDto.getEmail());

        if (employee != null) {
            boolean isPasswordMatch =passwordEncoder.matches(loginDto.getPassword(), employee.getPassword());

            if (isPasswordMatch) {
                // Generate JWT token
                return jwtUtil.generateToken(employee.getEmail());
            }
        }

        return "Invalid username or password";

    }


}
