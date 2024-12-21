package com.tendrylooks.daw.main.controller;

import com.tendrylooks.daw.main.dto.*;
import com.tendrylooks.daw.main.entity.User;
import com.tendrylooks.daw.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponseDto> createUser(@RequestBody UserCreateDto userCreateDto) {
        try {
            userService.createUser(userCreateDto);
            return ResponseEntity.ok(new ApiResponseDto("User created successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponseDto("Error creating user: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
        try {
            Optional<User> usuarioOpt = userService.verifyCredentials(loginDto);
            if (usuarioOpt.isPresent()) {
                User usuario = usuarioOpt.get();
                int roleInt = usuario.getRolUsu(); // '0' for user, '1' for admin

                String role = (roleInt == 1) ? "admin" : "user";

                String token = UUID.randomUUID().toString();

                return ResponseEntity.ok()
                        .header("token", token)
                        .header("role", role)  // Add the mapped user role (string) in the header
                        .body(new LoginResponseDto("Login successful", new UserDto(
                                usuario.getCodUsu(),
                                usuario.getNomUsu(),
                                usuario.getApeUsu(),
                                usuario.getCorreoUsu()
                        )));
            }
            return ResponseEntity.status(403).body(new LoginResponseDto("Invalid credentials. Please try again", null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new LoginResponseDto("Error loging user: " + e.getMessage(), null));
        }
    }
}
