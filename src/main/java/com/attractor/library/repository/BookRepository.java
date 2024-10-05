package com.attractor.library.repository;

import com.attractor.library.entity.Book;
import com.attractor.library.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategory(Category category);
    List<Book> findByAvailableTrue();

    List<Book> findByTitleContainingIgnoreCase(String title);
}
