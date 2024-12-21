package com.tendrylooks.daw.main.service;

import com.tendrylooks.daw.main.dto.LoginDto;
import com.tendrylooks.daw.main.dto.UserCreateDto;
import com.tendrylooks.daw.main.dto.UserDetailDto;
import com.tendrylooks.daw.main.dto.UserDto;
import com.tendrylooks.daw.main.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    void createUser(UserCreateDto userCreateDto);
    void updateUser(UserDetailDto userDetailDto);
    UserDetailDto findById(Integer codUsu);
    Page<UserDto> getAllUsers(Pageable pageable);
    Optional<User> verifyCredentials(LoginDto loginDto);
}
