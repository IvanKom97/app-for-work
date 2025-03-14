package com.example.projectforwork.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class JwtResponse {
    @Schema(description = "дто для ответа после успешной аутентификации." +
            " Выдает токен на фронт и прокидывает дальше")
    private String token;
    private String type;

    public JwtResponse(String token) {
        this.token = token;
        this.type = "Bearer";
    }
}