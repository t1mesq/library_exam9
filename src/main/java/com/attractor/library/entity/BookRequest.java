package com.attractor.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Читательский билет не может быть пустым")
    private String readerTicketNumber;

    @NotNull(message = "Книга должна быть указана")
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @NotNull(message = "Пользователь должен быть указан")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull(message = "Дата возврата должна быть указана")
    private LocalDate returnDate;

    public void setBookId(Long bookId) {
        this.book = new Book();
        this.book.setId(bookId);
    }
}
