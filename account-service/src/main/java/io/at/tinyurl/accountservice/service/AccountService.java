package io.at.tinyurl.accountservice.service;

import org.springframework.stereotype.Service;

import io.at.tinyurl.accountservice.dto.BaseResponse;
import io.at.tinyurl.accountservice.dto.CreateAccountRequest;
import io.at.tinyurl.accountservice.dto.GetAccountResponse;
import io.at.tinyurl.accountservice.dto.UpdateAccountRequest;

@Service
public interface AccountService {

    public BaseResponse createAccount(CreateAccountRequest request);

    public BaseResponse updateAccount(String username, UpdateAccountRequest account);
    
    public GetAccountResponse getAccount(String username);

    public String getProfile(String token);

    public BaseResponse deleteAccount(String username);

}
