package io.at.tinyurl.accountservice.dto;

import io.at.tinyurl.accountservice.model.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateAccountRequest extends BaseResponse {

    private Account account;
    
}
