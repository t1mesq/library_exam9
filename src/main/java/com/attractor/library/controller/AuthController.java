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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "registration";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute UserDTO userDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            return "registration";
        }

        userService.registerUser(userDTO, userDTO.getPassword());
        logger.info("User registered successfully: {}", userDTO.getReaderTicketNumber());
        return "redirect:/registration-success";
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserDTO userDTO) {
        User user = userService.authenticate(userDTO.getReaderTicketNumber(), userDTO.getPassword());
        if (user != null) {
            logger.info("User authenticated successfully: {}", user.getReaderTicketNumber());
            return ResponseEntity.ok(new AuthResponse("Authentication successful", user.getReaderTicketNumber()));
        } else {
            logger.warn("Unauthorized access attempt for reader ticket number: {}", userDTO.getReaderTicketNumber());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Unauthorized", null));
        }
    }
}
