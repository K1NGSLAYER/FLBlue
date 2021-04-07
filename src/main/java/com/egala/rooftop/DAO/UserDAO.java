package com.egala.rooftop.DAO;

import com.egala.rooftop.DTO.UpdateUserNameRequest;
import com.egala.rooftop.DTO.UpdateUserNameResponse;
import com.egala.rooftop.model.UserEntity;

import java.util.List;

public interface UserDAO {

    public UserEntity getUser(String email);

    public UserEntity getUserByID(String userID);

    public UserEntity createUser(UserEntity userEntity) throws Exception;

    public boolean updateUser(UserEntity userEntity);

    public List<UserEntity> getAllUsers();

    public UserEntity deleteUser(UserEntity userEntity);

    public boolean userExists(String email);

    public UpdateUserNameResponse updateUserName(UpdateUserNameRequest updateUserNameRequest);
}
