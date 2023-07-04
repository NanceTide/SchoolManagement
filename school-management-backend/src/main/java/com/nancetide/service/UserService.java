package com.nancetide.service;

import com.nancetide.entity.User;
import lombok.NonNull;

public interface UserService {

    User login(String username, String password);

    Boolean studentUpdateUser(@NonNull String username, @NonNull String oldPassword, @NonNull String newPassword);

    Boolean adminUpdateUser(@NonNull String username, @NonNull String newPassword);

}
