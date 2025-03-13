package com.example.projectforwork.dto;

import com.example.projectforwork.enums.Priorities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderDtoFullInfo {

    private String description;
    private LocalDate publicationDate;
    private Priorities priorities;

}
