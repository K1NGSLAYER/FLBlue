package com.egala.rooftop.DTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@Component
public class SignUpUserResponse implements Serializable {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 5591854792523544152L;

    private String confirmationCode;
    private String firstName;
    private String lastName;
    private String email;
    private String qrCode;
    private String role;
    private String status;
    private Boolean emailVerifcationStatus;
}
