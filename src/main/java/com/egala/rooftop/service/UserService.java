package com.egala.rooftop.service;

import com.egala.rooftop.DTO.*;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public SignUpUserResponse createUser(SignUpUserRequest userDTO) throws Exception;
    public SignInResponse getSignInUser(String userID);
    public boolean validateUser(SignUpUserRequest userDTO);

    public UpdateUserNameResponse updateUserName(UpdateUserNameRequest updateUserNameRequest) throws Exception;
}
