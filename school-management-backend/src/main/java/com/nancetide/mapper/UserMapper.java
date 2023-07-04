package com.nancetide.mapper;

import com.nancetide.entity.User;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectUserByCriteria(String username, String password);

    Integer updatePasswordByCriteria(@NonNull String username, String oldPassword, @NonNull String newPassword);

}
