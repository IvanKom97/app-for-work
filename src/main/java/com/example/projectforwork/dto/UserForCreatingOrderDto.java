package com.example.projectforwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserForCreatingOrderDto {
    private String mail;
    private OrderDto orderDto;
}
