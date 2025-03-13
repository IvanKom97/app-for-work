package com.example.projectforwork.mappers;

import com.example.projectforwork.dto.FullOrderDto;
import com.example.projectforwork.dto.OrderDto;
import com.example.projectforwork.dto.OrderDtoForAdmin;
import com.example.projectforwork.dto.OrderDtoForWorker;
import com.example.projectforwork.entity.OrderEntity;
import com.example.projectforwork.entity.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface OrderMapper {

    static OrderEntity fromOrderDtoToOrderEntity(OrderDto orderDto, UserEntity user) {
        return OrderEntity.builder()
                .orderDto(orderDto)
                .user(user)
                .build();
    }

    static FullOrderDto fromEntityToFullOrderDto(OrderEntity order) {
        return new FullOrderDto(order.getOrderStatus(),
                order.getTypesRepairs(),
                order.getOptionsWork(),
                order.getUser().getMail(),
                order.getWorker() != null ? order.getWorker().getMail() : "doesnt have worker",
                order.getPriorities(),
                order.getPublicationDate(),
                order.getInProcessSinceDate(),
                order.getPrice());
    }

    static OrderDtoForWorker fromEntityToOrderDtoForWorker(OrderEntity order) {
        return OrderDtoForWorker.builder()
                .userMail(order.getUser().getMail())
                .price(order.getPrice())
                .publicationDate(order.getPublicationDate())
                .option(order.getOptionsWork())
                .build();
    }

    static OrderDtoForAdmin fromOrderEntityToOrderDtoForAdmin(OrderEntity order) {
        return OrderDtoForAdmin.builder()
                .status(order.getOrderStatus())
                .priorities(order.getPriorities())
                .firstNameWorker(order.getWorker() != null ? order.getWorker().getFirstName() : "worker doesnt exist")
                .secondNameWorker(order.getWorker() != null ? order.getWorker().getSecondName() : "doesnt have worker")
                .mailWorker(order.getWorker() != null ? order.getWorker().getMail() : "doesnt have worker")
                .typesRepair(order.getTypesRepairs())
                .whenStarted(order.getPublicationDate())
                .option(order.getOptionsWork())
                .publicationDate(order.getPublicationDate())
                .build();
    }


    static List<OrderDtoForAdmin> fromPageToListOrderDtoForAdmin(Page<OrderEntity> page) {
        return page.map(OrderMapper::fromOrderEntityToOrderDtoForAdmin)
                .stream().
                toList();
    }

}
