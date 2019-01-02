package com.clearbases.springbootjwt.services;

import com.clearbases.springbootjwt.model.User;
import com.clearbases.springbootjwt.model.UserDto;

import java.util.List;

public interface UserService {
    User findOne(String username);

    User save(UserDto user);

    List<User> findAll();

    User findById(int id);

    UserDto update(UserDto userDto);

    void delete(int id);
}
