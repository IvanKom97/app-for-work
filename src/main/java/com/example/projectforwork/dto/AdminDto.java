package com.example.projectforwork.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AdminDto {
    @Schema(description = "имя", example = "Ivan")
    private String firstName;
    @Schema(description = "фамилия", example = "Ivanov")
    private String secondName;
    @Pattern(regexp = "^\\w+@\\p{L}+\\.\\p{L}{2,10}$")
    @Schema(description = "маил", example = "example@mail.ru")
    private String mail;
    @Schema(description = "пароль", example = "password123")
    private String password;
    @Schema(description = "необходимый пароль для регистрации админа, своего рода валидация, пароль компании")
    private String secretCompanyKey;
}
