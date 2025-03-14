package com.example.projectforwork.dto;

import com.example.projectforwork.annotation.ValidWorkTypeForCategory;
import com.example.projectforwork.enums.OptionsWork;
import com.example.projectforwork.enums.OrderStatus;
import com.example.projectforwork.enums.Priorities;
import com.example.projectforwork.enums.TypesRepairs;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FullOrderDto {
    @Schema(description = "статус заказа")
    private OrderStatus status;
    @Schema(description = "категория заказа", example = "к чему заказ относится, например, работа по дому, обучение. STUDYING и тд")
    private TypesRepairs typesRepairs;
    @Schema(description = "конкретный тип работы",
            example = "узкое направление для исполнения. Обучение англ, программированию и тд. " +
                    "Пометка, не может иметь другой тип работы." +
                    "например: JAVA не может относиться к SECURITY. JAVA - часть категории обучения, т.е STUDYING")
    private OptionsWork option;
    @Schema(description = "маил юзера")
    private String userMail;
    @Schema(description = "маил работника")
    private String workerMail;
    @Schema(description = "приоритет задачи", example = "HIGH, по умолчанию MIDDLE")
    private Priorities priorities;
    @Schema(description = "день публикации заказа")
    private LocalDate publicationDate;
    @Schema(description = "с какого числа задача находится в статусе выполнения")
    private LocalDate inProcessSinceDate;
    @Schema(description = "цена за выполнение")
    private int price;
}
