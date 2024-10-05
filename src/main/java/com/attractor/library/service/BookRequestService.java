package com.attractor.library.service;

import com.attractor.library.dto.BookRequestDTO;
import com.attractor.library.entity.Book;
import com.attractor.library.entity.BookRequest;
import com.attractor.library.entity.User;
import com.attractor.library.repository.BookRepository;
import com.attractor.library.repository.BookRequestRepository;
import com.attractor.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BookRequestService {
    @Autowired
    private BookRequestRepository bookRequestRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<BookRequest> getAllRequests() {
        return bookRequestRepository.findAll();
    }

    public BookRequest saveRequest(BookRequestDTO bookRequestDTO) {
        Book book = bookRepository.findById(bookRequestDTO.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Книга с ID " + bookRequestDTO.getBookId() + " не найдена"));

        User user = userRepository.findByReaderTicketNumber(bookRequestDTO.getReaderTicketNumber())
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с читательским билетом " + bookRequestDTO.getReaderTicketNumber() + " не найден"));

        BookRequest bookRequest = new BookRequest();
        bookRequest.setBook(book);
        bookRequest.setUser(user);
        bookRequest.setReturnDate(bookRequestDTO.getReturnDate());

        return bookRequestRepository.save(bookRequest);
    }

    public BookRequest getRequestById(Long id) {
        return bookRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Заявка с ID " + id + " не найдена"));
    }
}

