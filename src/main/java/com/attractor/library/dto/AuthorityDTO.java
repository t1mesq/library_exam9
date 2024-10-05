package com.attractor.library.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityDTO {
    private Long id;

    @NotBlank(message = "Название роли не может быть пустым")
    private String name;
}
