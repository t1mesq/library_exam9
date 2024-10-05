package com.attractor.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
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
    private String passportNumber;

    @NotBlank(message = "Пароль не может быть пустым")
    private String password;

    @NotBlank(message = "Номер читательского билета не может быть пустым")
    private String readerTicketNumber;

    private boolean enabled;

    private Set<AuthorityDTO> authorities;
}

