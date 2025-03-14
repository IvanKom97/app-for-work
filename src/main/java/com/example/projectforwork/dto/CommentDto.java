package com.example.projectforwork.dto;

import com.example.projectforwork.enums.Roles;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CommentDto {
    @Schema(description = "комментарий", example = "Ускорься родной")
    private String comment;
    @Schema(description = "дата публикации")
    private String datePublication;
    @Schema(description = "маил", example = "example@mail.ru")
    private String mail;
    @Schema(description = "роль", example = "ADMIN")
    private Roles role;
}
