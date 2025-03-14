package com.example.projectforwork.dto;

import com.example.projectforwork.annotation.ValidWorkTypeForCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class JoinedDtoUserOrder {
    @Schema(description = "дто для регистрации юзера, содержащее основные поля, имя, фамилия, маил, пароль")
    private UserDtoForRegistration userDtoForRegistration;
    @Schema(description = "дто для формирования заказа. Проходит валидацию на отношение к необходимой категории")
    private OrderDto orderDto;
}
