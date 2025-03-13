package com.example.projectforwork.dto;

import com.example.projectforwork.annotation.ValidWorkTypeForCategory;
import com.example.projectforwork.enums.OptionsWork;
import com.example.projectforwork.enums.OrderStatus;
import com.example.projectforwork.enums.Priorities;
import com.example.projectforwork.enums.TypesRepairs;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FullOrderDto {
    private OrderStatus status;
    private TypesRepairs typesRepairs;
    private OptionsWork option;
    private String userMail;
    private String workerMail;
    private Priorities priorities;
    private LocalDate publicationDate;
    private LocalDate inProcessSinceDate;
    private int price;
}
