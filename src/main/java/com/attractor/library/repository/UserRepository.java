package com.attractor.library.repository;

import com.attractor.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPassportNumber(String passportNumber);
    User findByReaderTicketNumber(String readerTicketNumber); // Метод для поиска по номеру читательского билета
}
