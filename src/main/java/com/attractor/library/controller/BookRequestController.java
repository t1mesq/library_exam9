package com.attractor.library.controller;

import com.attractor.library.dto.BookRequestDTO;
import com.attractor.library.service.BookRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-requests")
public class BookRequestController {

    private final BookRequestService bookRequestService;

    @Autowired
    public BookRequestController(BookRequestService bookRequestService) {
        this.bookRequestService = bookRequestService;
    }

    @GetMapping
    public List<BookRequestDTO> getAllRequests() {
        return bookRequestService.getAllRequests();
    }


    @PostMapping
    public BookRequestDTO createRequest(@RequestBody BookRequestDTO bookRequestDTO) {
        return bookRequestService.createRequest(bookRequestDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookRequestDTO> getRequestById(@PathVariable Long id) {
        BookRequestDTO bookRequestDTO = bookRequestService.getRequestById(id);
        return bookRequestDTO != null ? ResponseEntity.ok(bookRequestDTO) : ResponseEntity.notFound().build();
    }
}
