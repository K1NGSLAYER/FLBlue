package com.egala.rooftop.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class SignInResponse {
    private String confirmationCode;
    private String email;
}
