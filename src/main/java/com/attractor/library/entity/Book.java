package com.attractor.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название книги не может быть пустым")
    private String title;

    @NotBlank(message = "Автор книги не может быть пустым")
    private String author;

    @NotBlank(message = "Категория книги не может быть пустой")
    private String category;

    private String image;

    @NotNull(message = "Доступность книги должна быть указана")
    private boolean available;
}
