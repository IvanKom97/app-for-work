package com.example.projectforwork.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageWithIdOrderDto {
    private UUID orderId;
    private int page;
    private int count;
}
