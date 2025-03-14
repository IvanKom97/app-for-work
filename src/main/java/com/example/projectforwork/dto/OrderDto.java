package com.example.projectforwork.dto;

import com.example.projectforwork.annotation.ValidWorkTypeForCategory;
import com.example.projectforwork.enums.OptionsWork;
import com.example.projectforwork.enums.TypesRepairs;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ValidWorkTypeForCategory
public class OrderDto {
    @Schema(description = "Категория работы", example = "STUDYING")
    private TypesRepairs typesRepairs;
    @Schema(description = "Тип выполняемой работы", example = "ENGLISH")
    private OptionsWork option;
    @Schema(description = "Прайс за услугу, которую готов предложить юзер", example = "100")
    private int price;
}
