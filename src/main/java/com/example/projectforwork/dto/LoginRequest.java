package com.example.projectforwork.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {
    @Schema(description = "дто для аутентификации. Поле запрашивающее маил", example = "example@mail.ru")
    private String username;
    @Schema(description = "поле запрашивающее пароль", example = "password123")
    private String password;
}
