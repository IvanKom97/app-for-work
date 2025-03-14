package com.example.projectforwork.dto;

import com.example.projectforwork.annotation.ValidWorkTypeForCategory;
import com.example.projectforwork.enums.OptionsWork;
import com.example.projectforwork.enums.TypesRepairs;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderMasterDto {
    @Schema(description = "айдишник заказа", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID orderID;
    @Schema(description = "маил нового работника, который будет брать заказ другого мастера", example = "example@mail.ru")
    private String mailOfNewWorker;
}
