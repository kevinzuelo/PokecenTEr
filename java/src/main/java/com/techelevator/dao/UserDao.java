package com.techelevator.dao;

import com.techelevator.exception.UserExistsException;
import com.techelevator.model.User;
import com.techelevator.model.UserUpdate;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(int userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role, String email, String continent, String iconUrl);

    User getUserByCollectionId(int collectionId);

    boolean updateUser(UserUpdate user);
}
