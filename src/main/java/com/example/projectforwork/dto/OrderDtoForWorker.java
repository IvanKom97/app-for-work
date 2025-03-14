package com.example.projectforwork.dto;

import com.example.projectforwork.annotation.ValidWorkTypeForCategory;
import com.example.projectforwork.enums.OptionsWork;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;


@Builder
@Getter
@Setter
@ValidWorkTypeForCategory
public class OrderDtoForWorker {
    @Schema(description = "Тип выполняемой работы")
    private OptionsWork option;
    @Schema(description = "Маил юзера")
    private String userMail;
    @Schema(description = "Дата публикации заказа")
    private LocalDate publicationDate;
    @Schema(description = "Стоимость услуги")
    private int price;
}
