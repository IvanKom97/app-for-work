package com.example.projectforwork.dto;

import com.example.projectforwork.enums.OptionsWork;
import com.example.projectforwork.enums.OrderStatus;
import com.example.projectforwork.enums.Priorities;
import com.example.projectforwork.enums.TypesRepairs;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.retry.annotation.Backoff;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class OrderDtoForAdmin {
    @Schema(description = "Тип выполняемой работы")
    private OptionsWork option;
    @Schema(description = "Категория выполняемой работы")
    private TypesRepairs typesRepair;
    @Schema(description = "Имя работника, выполняющего задачу")
    private String firstNameWorker;
    @Schema(description = "Фамилия работника, выполняющего задачу")
    private String secondNameWorker;
    @Schema(description = "Маил работника")
    private String mailWorker;
    @Schema(description = "Дата публикации заказа")
    private LocalDate publicationDate;
    @Schema(description = "Статус заказа")
    private OrderStatus status;
    @Schema(description = "Приоритет заказа")
    private Priorities priorities;
    @Schema(description = "Дата, с которой заказ находится в разработке")
    private LocalDate whenStarted;
}
