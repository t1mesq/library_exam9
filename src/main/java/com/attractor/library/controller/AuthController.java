package com.attractor.library.controller;

import com.attractor.library.dto.AuthResponse;
import com.attractor.library.dto.UserDTO;
import com.attractor.library.entity.User;
import com.attractor.library.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    private static final    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserDTO userDTO, @RequestParam String confirmPassword, Model model) {
        logger.info("Попытка регистрации пользователя: {}", userDTO);

        try {
            User registeredUser = userService.registerUser(userDTO, confirmPassword);
            logger.info("Пользователь успешно зарегистрирован: {}", registeredUser);
            model.addAttribute("successMessage", registeredUser.getReaderTicketNumber());
            return "registration-success";
        } catch (IllegalArgumentException e) {
            logger.error("Ошибка при регистрации пользователя: {}", e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            return "registration";
        }
    }



    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserDTO userDTO) {
        User user = userService.authenticate(userDTO.getReaderTicketNumber(), userDTO.getPassword());
        if (user != null) {
            return ResponseEntity.ok(new AuthResponse("Authentication successful", user.getReaderTicketNumber()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Unauthorized", null));
        }
    }
}
