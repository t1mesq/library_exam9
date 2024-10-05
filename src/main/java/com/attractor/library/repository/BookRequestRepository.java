package com.attractor.library.repository;

import com.attractor.library.entity.BookRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRequestRepository extends JpaRepository<BookRequest, Long> {

}
