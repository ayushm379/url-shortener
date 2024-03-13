package io.at.tinyurl.accountservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.at.tinyurl.accountservice.dto.BaseResponse;
import io.at.tinyurl.accountservice.dto.LoginRequest;
import io.at.tinyurl.accountservice.dto.LoginResponse;
import io.at.tinyurl.accountservice.model.Account;
import io.at.tinyurl.accountservice.repository.AccountRepository;
import io.at.tinyurl.accountservice.service.AuthenticationService;
import io.at.tinyurl.accountservice.service.TokenService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse loginService(LoginRequest loginRequest) {
        Account account = accountRepository.getAccountByUsernameAndIsDeleted(loginRequest.getUsername(), false);
        if(!passwordEncoder.matches(loginRequest.getPassword(), account.getPassword())) {
            throw new RuntimeException("Password does not match");
        }
        return this.tokenService.generateToken(account);
    }

    @Override
    public BaseResponse logoutService(String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logoutService'");
    }

    
}
