package com.egala.rooftop.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class UpdateUserNameRequest {
    private String firstName;
    private String lastName;
    private String userID;
}
