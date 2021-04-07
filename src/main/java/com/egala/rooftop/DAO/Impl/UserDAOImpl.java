package com.egala.rooftop.DAO.Impl;
import com.egala.rooftop.DAO.UserDAO;
import com.egala.rooftop.DTO.UpdateUserNameRequest;
import com.egala.rooftop.DTO.UpdateUserNameResponse;
import com.egala.rooftop.model.UserEntity;
import com.egala.rooftop.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class UserDAOImpl implements UserDAO {
    Logger LOGGER = LoggerFactory.getLogger(PlayerDAOImpl.class);
    private UserRepository userRepository;
    @Autowired
    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        return userEntity;
    }

    @Override
    public UserEntity getUserByID(String userID) {
        //findOne(new Query(where("name").is("Joe")), Person.class));
        UserEntity userEntity = userRepository.findByUserID(userID);
        return userEntity;
    }

    @Override
    public boolean userExists(String email) {
        Optional<UserEntity> existingUser = userRepository.findById(email);
        return existingUser.isPresent();
    }

    @Override
    public UpdateUserNameResponse updateUserName(UpdateUserNameRequest updateUserNameRequest) {
        UserEntity user = null;
        UpdateUserNameResponse updateUserNameResponse = new UpdateUserNameResponse();
        user = userRepository.findByUserID(updateUserNameRequest.getUserID());
        if (user != null){
            user.setFirstName(updateUserNameRequest.getFirstName());
            user.setLastName(updateUserNameRequest.getLastName());
            user = userRepository.save(user);
            BeanUtils.copyProperties(user,updateUserNameResponse);
        }
        return updateUserNameResponse;
    }


    @Override
    public UserEntity createUser(UserEntity userEntity) throws Exception{

        /* Checks UserRepository if user is already signed up
         * Insert new User into UserRepository
         * */

        UserEntity insertUserEntityResponse = null;
        try {
            Optional<UserEntity> OptionalPlayer = Optional.of(new UserEntity());
            OptionalPlayer = userRepository.findById(userEntity.getEmail());
            boolean playerExists = OptionalPlayer.isPresent();
            if (playerExists) {
                throw new Exception();
            }
            insertUserEntityResponse = userRepository.insert(userEntity);
        } catch (Exception ex) {
            LOGGER.error("User {} is already registered {}", userEntity.getUserID(), ex.getMessage());
        }
        return insertUserEntityResponse;
    }

    @Override
    public boolean updateUser(UserEntity userEntity) {
        UserEntity user = userRepository.findByEmail(userEntity.getEmail());
        BeanUtils.copyProperties(userEntity,user);
        userRepository.save(user);
        return true;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> allUserEntities = userRepository.findAll();
        return allUserEntities;
    }

    @Override
    public UserEntity deleteUser(UserEntity userEntity) {
        UserEntity user = userRepository.findByEmail(userEntity.getEmail());
        userRepository.deleteById(user.getEmail());
        return user;
    }
}
