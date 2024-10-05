package com.attractor.library.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;

    @NotBlank(message = "Название книги не может быть пустым")
    private String title;

    @NotBlank(message = "Автор книги не может быть пустым")
    private String author;

    @NotBlank(message = "Категория книги не может быть пустой")
    private Long categoryId;

    private String image;

    private boolean available;
}
