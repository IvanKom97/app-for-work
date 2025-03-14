package com.example.projectforwork.dto;

import com.example.projectforwork.enums.OptionsWork;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class WorkerDto {
    @Schema(description = "Имя")
    private String firstName;
    @Schema(description = "Фамилия")
    private String secondName;
    @Pattern(regexp = "^\\w+@\\p{L}+\\.\\p{L}{2,10}")
    private String mail;
    @Schema(description = "Пароль")
    private String password;
    @Schema(description = "Тип выполняемой работы")
    private OptionsWork workType;
}
