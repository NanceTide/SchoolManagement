package com.nancetide.service.impl;

import com.nancetide.entity.User;
import com.nancetide.mapper.UserMapper;
import com.nancetide.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User login(String username, String password) {
        List<User> userList = userMapper.selectUserByCriteria(username, password);
        if(!userList.isEmpty())
            return userList.get(0);
        return null;
    }

    @Override
    public Boolean studentUpdateUser(@NonNull String username, @NonNull String oldPassword, @NonNull String newPassword) {
        return userMapper.updatePasswordByCriteria(username, oldPassword, newPassword) == 1;
    }

    @Override
    public Boolean adminUpdateUser(@NonNull String username, @NonNull String newPassword) {
        return userMapper.updatePasswordByCriteria(username, null, newPassword) == 1;
    }


}
