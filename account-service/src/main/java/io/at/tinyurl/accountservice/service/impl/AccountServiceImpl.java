package io.at.tinyurl.accountservice.service.impl;

import org.springframework.stereotype.Service;

import io.at.tinyurl.accountservice.dto.BaseResponse;
import io.at.tinyurl.accountservice.dto.CreateAccountRequest;
import io.at.tinyurl.accountservice.dto.GetAccountResponse;
import io.at.tinyurl.accountservice.dto.UpdateAccountRequest;
import io.at.tinyurl.accountservice.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public BaseResponse createAccount(CreateAccountRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAccount'");
    }

    @Override
    public BaseResponse updateAccount(String username, UpdateAccountRequest account) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAccount'");
    }

    @Override
    public GetAccountResponse getAccount(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccount'");
    }

    @Override
    public String getProfile(String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProfile'");
    }

    @Override
    public BaseResponse deleteAccount(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAccount'");
    }

    
}
