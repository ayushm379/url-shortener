package io.at.tinyurl.accountservice.service;

import org.springframework.stereotype.Service;

import io.at.tinyurl.accountservice.dto.LoginResponse;
import io.at.tinyurl.accountservice.model.Account;

@Service
public interface TokenService {

    public LoginResponse generateToken(Account account);

    public String getUsername(String token);

    public boolean validate(String token);
    
}
