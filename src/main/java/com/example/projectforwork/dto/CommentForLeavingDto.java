package com.example.projectforwork.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CommentForLeavingDto {
    @Schema(description = "коментарий для заказа", example = "поторапливаемся, надо ускориться чутка с выполнением заказа")
    private String comment;
}
