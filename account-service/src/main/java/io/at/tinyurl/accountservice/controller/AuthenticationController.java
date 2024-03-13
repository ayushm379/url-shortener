package io.at.tinyurl.accountservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.at.tinyurl.accountservice.dto.BaseResponse;
import io.at.tinyurl.accountservice.dto.LoginRequest;
import io.at.tinyurl.accountservice.dto.LoginResponse;
import io.at.tinyurl.accountservice.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;


@RequestMapping
@RestController("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authenticationService.loginService(request);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/logout")
    public BaseResponse logout(@RequestHeader HttpHeaders headers) {
        return authenticationService.logoutService(headers.getFirst(HttpHeaders.AUTHORIZATION));
    }

}
