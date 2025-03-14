package com.example.projectforwork.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserDtoForAdmin {
    @Schema(description = "Имя")
    private String firstName;
    @Schema(description = "Фамилия")
    private String secondName;
    @Schema(description = "Маил")
    private String mail;
    @Schema(description = "Все заказы конкретного пользователя, " +
            "количество которых можно будет отображать и регулировать за счет пагинации в дальнейшем")
    private List<OrderDtoForAdmin> orders;
}
