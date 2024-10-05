package com.attractor.library.service;

import com.attractor.library.dto.UserDTO;
import com.attractor.library.entity.User;
import com.attractor.library.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(@Valid UserDTO userDTO, String confirmPassword) {
        if (!userDTO.getPassword().equals(confirmPassword)) {
            throw new IllegalArgumentException("Пароли не совпадают");
        }

        if (userRepository.findByPassportNumber(userDTO.getPassportNumber()) != null) {
            throw new IllegalArgumentException("Номер паспорта уже существует");
        }

        String readerTicketNumber;
        do {
            readerTicketNumber = generateReaderTicketNumber();
        } while (userRepository.findByReaderTicketNumber(readerTicketNumber) != null);

        User user = new User();
        user.setSurname(userDTO.getSurname());
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setPassportNumber(userDTO.getPassportNumber());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setReaderTicketNumber(readerTicketNumber);
        user.setEnabled(true);

        return userRepository.save(user);
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

    public User saveUser(User user) {
        return userRepository.save(user);
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
        Optional<User> optionalUser = userRepository.findByReaderTicketNumber(readerTicketNumber);
        if (optionalUser.isPresent() && passwordEncoder.matches(password, optionalUser.get().getPassword())) {
            return optionalUser.get();
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
