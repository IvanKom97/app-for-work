package com.example.projectforwork.dto;

import com.example.projectforwork.enums.OptionsWork;
import com.example.projectforwork.enums.OrderStatus;
import com.example.projectforwork.enums.Priorities;
import com.example.projectforwork.enums.TypesRepairs;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.retry.annotation.Backoff;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class OrderDtoForAdmin {

    private OptionsWork option;
    private TypesRepairs typesRepair;
    private String firstNameWorker;
    private String secondNameWorker;
    private String mailWorker;
    private LocalDate publicationDate;
    private OrderStatus status;
    private Priorities priorities;
    private LocalDate whenStarted;
}
