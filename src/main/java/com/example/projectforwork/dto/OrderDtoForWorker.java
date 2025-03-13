package com.example.projectforwork.dto;

import com.example.projectforwork.annotation.ValidWorkTypeForCategory;
import com.example.projectforwork.enums.OptionsWork;
import lombok.*;

import java.time.LocalDate;


@Builder
@Getter
@Setter
@ValidWorkTypeForCategory
public class OrderDtoForWorker {
    private OptionsWork option;
    private String userMail;
    private LocalDate publicationDate;
    private int price;
}
