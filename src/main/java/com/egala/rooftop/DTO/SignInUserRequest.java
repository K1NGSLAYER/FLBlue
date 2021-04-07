package com.egala.rooftop.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInUserRequest {
    private String email;
    private String password;
}
