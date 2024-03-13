package io.at.tinyurl.accountservice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse extends BaseResponse {
    
    private String token;

    private Date expiresAt;

}
