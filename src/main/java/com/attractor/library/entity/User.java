package com.attractor.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Фамилия не может быть пустым")
    private String surname;

    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    private String patronymic;

    @NotBlank(message = "Адрес не может быть пустым")
    private String address;

    @NotBlank(message = "Номер паспорта не может быть пустым")
    @Size(min = 6, max = 12, message = "Номер паспорта должен содержать от 6 до 12 символов")
    @Column(unique = true)
    private String passportNumber;

    @NotBlank(message = "Пароль не может быть пустым")
    private String password;
}