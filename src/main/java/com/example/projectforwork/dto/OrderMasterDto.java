package com.example.projectforwork.dto;

import com.example.projectforwork.annotation.ValidWorkTypeForCategory;
import com.example.projectforwork.enums.OptionsWork;
import com.example.projectforwork.enums.TypesRepairs;
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
    private UUID orderID;
    private String mailOfNewWorker;
}
