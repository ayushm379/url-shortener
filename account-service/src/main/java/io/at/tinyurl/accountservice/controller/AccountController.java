package io.at.tinyurl.accountservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.at.tinyurl.accountservice.dto.BaseResponse;
import io.at.tinyurl.accountservice.dto.CreateAccountRequest;
import io.at.tinyurl.accountservice.dto.GetAccountResponse;
import io.at.tinyurl.accountservice.dto.UpdateAccountRequest;
import io.at.tinyurl.accountservice.service.AccountService;


@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    
    @PostMapping("/")
    public BaseResponse createAccount(@RequestBody CreateAccountRequest request) {
        return accountService.createAccount(request);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{username}")
    public GetAccountResponse getAccount(@PathVariable String username) {
        return accountService.getAccount(username);   
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/profile")
    public String getProfile(@RequestHeader HttpHeaders headers) {
        return accountService.getProfile(headers.getFirst(HttpHeaders.AUTHORIZATION));
    }
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{username}")
    public BaseResponse deleteAccount(@PathVariable String username) {
        return accountService.deleteAccount(username);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{username}")
    public BaseResponse updateAccount(@PathVariable String username, @RequestBody UpdateAccountRequest request) {
        return accountService.updateAccount(username, request);
    }

}
