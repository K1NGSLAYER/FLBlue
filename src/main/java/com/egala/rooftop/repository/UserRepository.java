package com.egala.rooftop.repository;

import com.egala.rooftop.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    public UserEntity findByEmail(String email);
    public UserEntity findByUserID(String userID);
    @Override
    public UserEntity insert(UserEntity userEntity);

    @Override
    public void deleteById(String email);
}
