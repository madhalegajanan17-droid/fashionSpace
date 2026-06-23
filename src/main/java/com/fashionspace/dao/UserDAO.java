package com.fashionspace.dao;

import com.fashionspace.model.User;

public interface UserDAO {

    boolean registerUser(User user);

    User loginUser(String email, String password);

    User getUserById(int userId);

    boolean updateUser(User user);

    boolean deleteUser(int userId);

    boolean isEmailExists(String email);

    boolean updatePassword(
            String email,
            String newPassword
    );
}