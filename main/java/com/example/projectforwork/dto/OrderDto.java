package com.example.projectforwork.dto;

import com.example.projectforwork.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderDto {
    private String description;
}
