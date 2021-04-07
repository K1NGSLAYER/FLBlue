package com.egala.rooftop.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@Document(collection = "users")
public class UserEntity {
    private String id;
    private String firstName;
    private String lastName;
    private String userID;
    @Id
    private String email;
    private String role;
    private String encryptedPassword;
    private String emailVerificationToken;
    private Boolean emailVerifcationStatus = false;
}
