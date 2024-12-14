package com.tendrylooks.daw.main.service.impl;

import com.tendrylooks.daw.main.dto.LoginDto;
import com.tendrylooks.daw.main.dto.UserCreateDto;
import com.tendrylooks.daw.main.entity.User;
import com.tendrylooks.daw.main.repository.UserRepository;
import com.tendrylooks.daw.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(UserCreateDto userCreateDto) {
        userRepository.createUser(
                userCreateDto.nomUsu(),
                userCreateDto.apeUsu(),
                userCreateDto.correoUsu(),
                userCreateDto.contraUsu(),
                userCreateDto.rolUsu()
        );
    }

    @Override
    public Optional<User> verifyCredentials(LoginDto loginDto) {
        Optional<User> userOpt = userRepository.findByCorreoUsu(loginDto.email());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getContraUsu().equals(loginDto.password()) && user.getEstUsu() == 1) {
                return userOpt; // Credenciales válidas
            }
        }
        return Optional.empty();
    }
}
