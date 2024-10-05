package com.attractor.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

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

    @NotBlank(message = "Номер читательского билета не может быть пустым")
    @Column(unique = true)
    private String readerTicketNumber;

    @NotBlank
    private String role;

    private boolean enabled = true;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }
}