package com.attractor.library.service;

import com.attractor.library.entity.User;
import com.attractor.library.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(@Valid User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        return userRepository.save(user);
    }

    public User findByPassportNumber(String passportNumber) {
        return userRepository.findByPassportNumber(passportNumber);
    }

    public User findByReaderTicketNumber(String readerTicketNumber) {
        return userRepository.findByReaderTicketNumber(readerTicketNumber);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }
}