package com.scm.Secure.Multi_User.Contact.Management.System.services;

import java.util.List;
import java.util.Optional;

import com.scm.Secure.Multi_User.Contact.Management.System.entities.User;

public interface UserService {

    User saveUser(User user);

    Optional<User> getUserById(String id);

    Optional<User> updateUSer(User user);

    void deleteUser(String id);

    boolean isUserExist(String userId);

    boolean isUserExistByEmail(String email);

    List<User> getAllUsers();

    User getUserByEmail(String email);

}
