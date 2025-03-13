package com.example.projectforwork.dto;

import com.example.projectforwork.enums.Priorities;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class OrderIdPriorityDto {
    private UUID orderId;
    private Priorities priorities;
}
