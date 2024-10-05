package com.attractor.library.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
