package io.at.tinyurl.accountservice.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class BaseResponse {
    
    private boolean success;
    
    private HttpStatus status;

}
