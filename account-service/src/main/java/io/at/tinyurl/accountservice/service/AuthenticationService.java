package io.at.tinyurl.accountservice.service;

import org.springframework.stereotype.Service;

import io.at.tinyurl.accountservice.dto.BaseResponse;
import io.at.tinyurl.accountservice.dto.LoginRequest;
import io.at.tinyurl.accountservice.dto.LoginResponse;

@Service
public interface AuthenticationService {

    public LoginResponse loginService(LoginRequest LoginRequest);

    public BaseResponse logoutService(String token);
    
}
