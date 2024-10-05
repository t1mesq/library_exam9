package com.attractor.library.service;

import com.attractor.library.dto.UserDTO;
import com.attractor.library.entity.Authority;
import com.attractor.library.entity.User;
import com.attractor.library.entity.UserAuthority;
import com.attractor.library.repository.AuthorityRepository;
import com.attractor.library.repository.UserAuthoritiesRepository;
import com.attractor.library.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserAuthoritiesRepository userAuthoritiesRepository;

    public User registerUser(UserDTO userDTO, String password) {
        if (userRepository.existsByPassportNumber(userDTO.getPassportNumber())) {
            throw new RuntimeException("Номер паспорта уже существует.");
        }

        User user = new User();
        user.setSurname(userDTO.getSurname());
        user.setName(userDTO.getName());
        user.setPatronymic(userDTO.getPatronymic());
        user.setAddress(userDTO.getAddress());
        user.setPassportNumber(userDTO.getPassportNumber());
        user.setReaderTicketNumber(userDTO.getReaderTicketNumber());
        user.setPassword(passwordEncoder.encode(password));
        user.setAuthorities(Set.of(authorityRepository.findByName("USER")));
        userRepository.save(user);
        return user;
    }

    private String generateReaderTicketNumber() {
        return UUID.randomUUID().toString();
    }


    public User findByPassportNumber(String passportNumber) {
        return userRepository.findByPassportNumber(passportNumber)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с номером паспорта " + passportNumber + " не найден"));
    }

    public User findByReaderTicketNumber(String readerTicketNumber) {
        return userRepository.findByReaderTicketNumber(readerTicketNumber)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с читательским билетом " + readerTicketNumber + " не найден"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с ID " + id + " не найден"));
    }

    public User updateUser(Long id, User user) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Пользователь с ID " + id + " не найден");
        }
        user.setId(id);
        return userRepository.save(user);
    }


    public User authenticate(String readerTicketNumber, String password) {
        logger.info("Аутентификация пользователя с номером читательского билета: {}", readerTicketNumber);

        Optional<User> optionalUser = userRepository.findByReaderTicketNumber(readerTicketNumber);

        if (optionalUser.isPresent()) {
            logger.info("Пользователь найден: {}", optionalUser.get().getUsername());
            boolean passwordMatch = passwordEncoder.matches(password, optionalUser.get().getPassword());
            logger.info("Пароль совпадает: {}", passwordMatch);

            if (passwordMatch) {
                return optionalUser.get();
            }
        } else {
            logger.warn("Пользователь не найден с номером читательского билета: {}", readerTicketNumber);
        }

        return null;
    }


    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Пользователь с ID " + id + " не найден");
        }
        userRepository.deleteById(id);
    }
}
