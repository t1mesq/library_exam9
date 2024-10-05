package com.attractor.library.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {
    private Long id;

    @NotNull(message = "Читательский билет не может быть пустым")
    private String readerTicketNumber;

    @NotNull(message = "Книга должна быть указана")
    private Long bookId;

    @NotNull(message = "Пользователь должен быть указан")
    private Long userId;

    @NotNull(message = "Дата возврата должна быть указана")
    private LocalDate returnDate;
}
