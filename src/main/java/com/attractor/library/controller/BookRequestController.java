package com.attractor.library.controller;

import com.attractor.library.dto.BookRequestDTO;
import com.attractor.library.entity.BookRequest;
import com.attractor.library.service.BookRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/book-requests")
@Validated
public class BookRequestController {
    @Autowired
    private BookRequestService bookRequestService;

    @GetMapping
    public List<BookRequest> getAllRequests() {
        return bookRequestService.getAllRequests();
    }

    @PostMapping
    public ResponseEntity<BookRequest> createRequest(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
        BookRequest bookRequest = new BookRequest();
        bookRequest.setReaderTicketNumber(bookRequestDTO.getReaderTicketNumber());
        bookRequest.setBookId(bookRequestDTO.getBookId());
        bookRequest.setReturnDate(bookRequestDTO.getReturnDate());
        BookRequest createdRequest = bookRequestService.saveRequest(bookRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookRequest> getRequestById(@PathVariable Long id) {
        BookRequest request = bookRequestService.getRequestById(id);
        return ResponseEntity.ok(request);
    }

}
