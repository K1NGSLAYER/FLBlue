package com.egala.rooftop.service.impl;

import com.egala.rooftop.DAO.UserDAO;
import com.egala.rooftop.DTO.*;
import com.egala.rooftop.model.UserEntity;
import com.egala.rooftop.service.UserService;
import com.egala.rooftop.utils.Utils;
import com.egala.rooftop.utils.ServiceConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserServiceImpl implements UserService {

    Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserDAO userDAO;

    private SignUpUserResponse signUpUserResponse;

    private SignInResponse signInResponse;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    Utils utils;

    @Autowired
    public UserServiceImpl(UserDAO userDAO,
                           SignUpUserResponse signUpUserResponse,
                           SignInResponse signInResponse,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           Utils utils) {
        this.userDAO = userDAO;
        this.signInResponse = signInResponse;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.signUpUserResponse = signUpUserResponse;
        this.utils = utils;
    }

    @Override
    public SignUpUserResponse createUser(SignUpUserRequest signUpUserRequest) throws Exception {
        UserEntity createUserEntityResult = new UserEntity();
        try {
            if (!userDAO.userExists(signUpUserRequest.getEmail())) {
                UserEntity newUserEntity = new UserEntity();
                BeanUtils.copyProperties(signUpUserRequest, newUserEntity);
                String userID = utils.generateConfirmationCode(9);
                newUserEntity.setUserID(userID);
                newUserEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(signUpUserRequest.getPassword()));
                createUserEntityResult = userDAO.createUser(newUserEntity);
                if (createUserEntityResult != null) {
                    BeanUtils.copyProperties(createUserEntityResult, signUpUserResponse);
                    signUpUserResponse.setStatus(ServiceConstants.SUCCESS);
                    System.out.println("Registration Successful");
                }

            } else {
                throw new RuntimeException(signUpUserRequest.getEmail() + "{} is already signed up ");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return signUpUserResponse;
    }

    @Override
    public SignInResponse getSignInUser(String userID) {
        UserEntity userEntity = userDAO.getUserByID(userID);
        signInResponse.setEmail(userEntity.getEmail());
        signInResponse.setConfirmationCode(userEntity.getUserID());
        System.out.println(signInResponse.getConfirmationCode());
        return signInResponse;
    }

    @Override
    public boolean validateUser(SignUpUserRequest userDTO) {
        return false;
    }

    @Override
    public UpdateUserNameResponse updateUserName(UpdateUserNameRequest updateUserNameRequest) throws Exception{
        UpdateUserNameResponse updateUserNameResponse = new UpdateUserNameResponse();

        try{
            updateUserNameResponse = userDAO.updateUserName(updateUserNameRequest);
            return updateUserNameResponse;
        }catch(Exception ex){
            LOGGER.error(ex.getMessage());
        }
        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntitySignInResponse = userDAO.getUser(email);
        if (signInResponse != null) {
            signInResponse.setEmail(userEntitySignInResponse.getEmail());
            signInResponse.setConfirmationCode(userEntitySignInResponse.getUserID());
            return new User(userEntitySignInResponse.getEmail(), userEntitySignInResponse.getEncryptedPassword(), new ArrayList<>());
        }
        throw new UsernameNotFoundException(email);
    }

}

