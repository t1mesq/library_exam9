package com.attractor.library.service;

import com.attractor.library.dto.BookDTO;
import com.attractor.library.entity.Book;
import com.attractor.library.entity.Category;
import com.attractor.library.repository.BookRepository;
import com.attractor.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import jakarta.persistence.EntityNotFoundException;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Книга с ID " + id + " не найдена"));
    }

    public Book saveBook(BookDTO bookDTO) {
        Category category = categoryRepository.findById(bookDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Категория с ID " + bookDTO.getCategoryId() + " не найдена"));

        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setCategory(category);
        book.setAvailable(bookDTO.isAvailable());

        return bookRepository.save(book);
    }

    public Book updateBook(Long id, BookDTO bookDTO) {
        Book book = getBookById(id);
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());

        Category category = categoryRepository.findById(bookDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Категория с ID " + bookDTO.getCategoryId() + " не найдена"));
        book.setCategory(category);
        book.setAvailable(bookDTO.isAvailable());

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }


    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
}

