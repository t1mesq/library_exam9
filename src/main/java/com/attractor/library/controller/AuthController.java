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
    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Неверный читательский билет или пароль.");
        }
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute UserDTO userDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            return "registration";
        }

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            model.addAttribute("errorMessage", "Пароли не совпадают.");
            model.addAttribute("userDTO", userDTO);
            return "registration";
        }

        userService.registerUser(userDTO, userDTO.getPassword());
        logger.info("User registered successfully: {}", userDTO.getReaderTicketNumber());
        return "redirect:/auth/registration-success";
    }

    @GetMapping("/registration-success")
    public String showRegistrationSuccessPage(Model model) {
        return "registration-success";
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        return "book-list";
    }
}
