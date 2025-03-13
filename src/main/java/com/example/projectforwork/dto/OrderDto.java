package com.example.projectforwork.dto;

import com.example.projectforwork.annotation.ValidWorkTypeForCategory;
import com.example.projectforwork.enums.OptionsWork;
import com.example.projectforwork.enums.TypesRepairs;
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
    private TypesRepairs typesRepairs;
    private OptionsWork option;
    private int price;
}
