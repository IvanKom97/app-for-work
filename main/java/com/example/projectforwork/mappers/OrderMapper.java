package com.example.projectforwork.mappers;

import com.example.projectforwork.dto.OrderDto;
import com.example.projectforwork.entity.OrderEntity;
import com.example.projectforwork.entity.UserEntity;

public interface OrderMapper {

    static OrderEntity fromOrderDtoToOrderEntity(OrderDto orderDto, UserEntity user) {
        return OrderEntity.builder()
                .descriptions(orderDto.getDescription())
                .user(user)
                .build();
    }
}
