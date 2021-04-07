package com.egala.rooftop.controller;

import com.egala.rooftop.DTO.*;
import com.egala.rooftop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
    @Autowired
    private UserService userService;

    @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public SignUpUserResponse addUser(@RequestBody SignUpUserRequest signUpUserRequest) throws Exception {

        //Create response object
        SignUpUserResponse createUserResponse;

        //Create userDTO
        SignUpUserRequest createUserRequestDTO = new SignUpUserRequest();

        //Populates userDTO properties
        BeanUtils.copyProperties(signUpUserRequest,createUserRequestDTO);

        //Invoke Service layer
        createUserResponse = userService.createUser(createUserRequestDTO);

        //Validate Response

       return createUserResponse;
    }

    @GetMapping(value = "/getuser/{userID}",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public SignInResponse getUser(@PathVariable String userID){
        SignInResponse response = new SignInResponse();
        response = userService.getSignInUser(userID);

        return response;
    }

    @PatchMapping(value = "/update/{userID}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public UpdateUserNameResponse updateUser(@NonNull @RequestBody UpdateUserNameRequest updateUserRequest, @PathVariable(required = true) String userID){
        UpdateUserNameResponse updateUserNameResponse = new UpdateUserNameResponse();
        updateUserRequest.setUserID(userID);
        try {
            updateUserNameResponse = userService.updateUserName(updateUserRequest);
            return updateUserNameResponse;
        }catch(Exception ex){
            LOGGER.error(ex.getMessage());
        }
        return null;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }
}
