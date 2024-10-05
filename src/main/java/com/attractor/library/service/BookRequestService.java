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
import java.util.stream.Collectors;

@Service
public class BookRequestService {
    @Autowired
    private BookRequestRepository bookRequestRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<BookRequestDTO> getAllRequests() {
        List<BookRequest> requests = bookRequestRepository.findAll();
        return requests.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BookRequestDTO createRequest(BookRequestDTO bookRequestDTO) {
        BookRequest bookRequest = convertToEntity(bookRequestDTO);
        BookRequest savedRequest = bookRequestRepository.save(bookRequest);
        return convertToDTO(savedRequest);
    }

    private BookRequest convertToEntity(BookRequestDTO bookRequestDTO) {
        BookRequest bookRequest = new BookRequest();
        bookRequest.setReaderTicketNumber(bookRequestDTO.getReaderTicketNumber());
        bookRequest.setReturnDate(bookRequestDTO.getReturnDate());
        Book book = bookRepository.findById(bookRequestDTO.getBookId()).orElse(null);
        bookRequest.setBook(book);
        return bookRequest;
    }

    private BookRequestDTO convertToDTO(BookRequest bookRequest) {
        BookRequestDTO dto = new BookRequestDTO();
        dto.setId(bookRequest.getId());
        dto.setBookId(bookRequest.getBook().getId());
        dto.setReaderTicketNumber(bookRequest.getReaderTicketNumber());
        dto.setReturnDate(bookRequest.getReturnDate());
        return dto;
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

    public BookRequestDTO getRequestById(Long id) {
        BookRequest bookRequest = bookRequestRepository.findById(id).orElse(null);
        return convertToDTO(bookRequest);
    }
}

