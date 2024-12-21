package com.tendrylooks.daw.main.service.impl;

import com.tendrylooks.daw.main.dto.LoginDto;
import com.tendrylooks.daw.main.dto.UserCreateDto;
import com.tendrylooks.daw.main.dto.UserDetailDto;
import com.tendrylooks.daw.main.dto.UserDto;
import com.tendrylooks.daw.main.entity.User;
import com.tendrylooks.daw.main.repository.UserRepository;
import com.tendrylooks.daw.main.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public void updateUser(UserDetailDto userDetailDto) {
        User existingUser = userRepository.findById(userDetailDto.codUsu())
                .orElseThrow(() -> new EntityNotFoundException("User not found for ID: " + userDetailDto.codUsu()));

        if (userDetailDto.nomUsu() != null) {
            existingUser.setNomUsu(userDetailDto.nomUsu());
        }
        if (userDetailDto.apeUsu() != null) {
            existingUser.setApeUsu(userDetailDto.apeUsu());
        }
        if (userDetailDto.correoUsu() != null) {
            existingUser.setCorreoUsu(userDetailDto.correoUsu());
        }
        if (userDetailDto.rolUsu() != null) {
            existingUser.setRolUsu(userDetailDto.rolUsu());
        }
        if(userDetailDto.estUsu() != null) {
            existingUser.setEstUsu(userDetailDto.estUsu());
        }

        userRepository.save(existingUser);
    }

    @Override
    public UserDetailDto findById(Integer codUsu) {
        User user = userRepository.findById(codUsu)
                .orElseThrow(() -> new EntityNotFoundException("User not found for ID: " + codUsu));

        return new UserDetailDto(
                user.getCodUsu(),
                user.getNomUsu(),
                user.getApeUsu(),
                user.getCorreoUsu(),
                user.getRolUsu(),
                user.getEstUsu(),
                user.getFecUsu()
        );
    }

    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        int limit = pageable.getPageSize();
        int offset = (int) pageable.getOffset();

        List<User> users = userRepository.spGetAllUsers(limit, offset);

        List<UserDto> userDtos = users.stream().map(this::convertToUserDto).collect(Collectors.toList());

        long totalUsers = userRepository.count();

        return new PageImpl<>(userDtos, pageable, totalUsers);
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

    private UserDto convertToUserDto(User user) {
        return new UserDto(
                user.getCodUsu(),
                user.getNomUsu(),
                user.getApeUsu(),
                user.getCorreoUsu(),
                user.getEstUsu()
        );
    }
}
