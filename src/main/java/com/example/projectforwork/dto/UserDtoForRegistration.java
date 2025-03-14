package com.example.projectforwork.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDtoForRegistration {
    @Schema(description = "Имя", example = "Ivan")
    private String firstName;
    @Schema(description = "Фамилия", example = "Ivanov")
    private String secondName;
    @Schema(description = "Маил, с проверкой на валидацию", example = "example@google.com")
    @Pattern(regexp = "^\\w+@\\p{L}+\\.\\p{L}{2,10}$")
    private String mail;
    @Schema(description = "Пароль", example = "password123")
    private String password;

}
