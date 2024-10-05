package com.attractor.library.controller;

import com.attractor.library.dto.AuthResponse;
import com.attractor.library.dto.UserDTO;
import com.attractor.library.entity.User;
import com.attractor.library.service.UserService;
import jakarta.validation.Valid;
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

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                               @RequestParam String confirmPassword,
                               Model model) {
        try {
            User registeredUser = userService.registerUser(userDTO, confirmPassword);
            model.addAttribute("successMessage", "Читательский билет создан: " + registeredUser.getReaderTicketNumber());
            return "registration_success";
        } catch (IllegalArgumentException e) {
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
