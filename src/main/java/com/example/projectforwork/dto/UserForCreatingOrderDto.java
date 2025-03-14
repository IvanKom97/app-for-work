package com.example.projectforwork.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserForCreatingOrderDto {
    @Schema(description = "Маил", example = "example@mail.ru")
    private String mail;
    @Schema(description = "Дто для формирования заказа для юзера с запрашиваемым маилом")
    private OrderDto orderDto;
}
