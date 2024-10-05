package com.attractor.library.repository;

import com.attractor.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPassportNumber(String passportNumber);
    Optional<User> findByReaderTicketNumber(String readerTicketNumber);

}
