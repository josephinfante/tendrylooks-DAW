package com.tendrylooks.daw.main.service;

import com.tendrylooks.daw.main.dto.LoginDto;
import com.tendrylooks.daw.main.dto.UserCreateDto;
import com.tendrylooks.daw.main.entity.User;

import java.util.Optional;

public interface UserService {
    void createUser(UserCreateDto userCreateDto);
    Optional<User> verifyCredentials(LoginDto loginDto);
}
