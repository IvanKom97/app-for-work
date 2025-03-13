package com.example.projectforwork.utils;

import com.example.projectforwork.dto.*;
import com.example.projectforwork.entity.AbstractUserEntity;
import com.example.projectforwork.entity.OrderEntity;
import com.example.projectforwork.entity.UserEntity;
import com.example.projectforwork.entity.WorkerEntity;
import com.example.projectforwork.enums.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestUtils {
    public static AbstractUserEntity abstractUser() {
        return AbstractUserEntity.builder()
                .firstName("ivan")
                .secondName("kom")
                .mail("iv@mail.ru")
                .password("pas")
                .role(Roles.USER)
                .build();
    }

    public static UserEntity user() {
        return new UserEntity("ivan", "kom", "iva@mail.ru", "gav");
    }

    public static UserDtoForRegistration userDtoForRegistration() {
        return UserDtoForRegistration.builder()
                .firstName("ivan")
                .secondName("kom")
                .mail("iv@mail.ru")
                .password("pas")
                .build();
    }

    public static OrderDto orderDto() {
        return OrderDto.builder()
                .price(1000)
                .option(OptionsWork.COOKING)
                .typesRepairs(TypesRepairs.HOME_WORK)
                .build();
    }

    public static OrderEntity orderEntity(UserEntity userEntity) {
        return OrderEntity.builder()
                .user(userEntity)
                .orderDto(orderDto())
                .build();
    }


    public static List<FullOrderDto> fullOrderDtoList() {
        List<FullOrderDto> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            FullOrderDto fullOrderDto = FullOrderDto.builder()
                    .price(1000 + i)
                    .option(OptionsWork.COOKING)
                    .status(OrderStatus.IN_PROCESS)
                    .publicationDate(LocalDate.of(2000, 1, 10))
                    .userMail("pes@mail.ru" + i)
                    .workerMail("qqq@mail.ru" + i)
                    .inProcessSinceDate(LocalDate.of(2010, 1, 15))
                    .priorities(Priorities.MIDDLE)
                    .typesRepairs(TypesRepairs.HOME_WORK)
                    .build();
            list.add(fullOrderDto);
        }
        return list;
    }

    public  static List<OrderEntity> orderEntitiesList() {
        List<OrderEntity> list = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            OrderEntity order = new OrderEntity(UUID.randomUUID(),
                    TypesRepairs.HOME_WORK,
                    OptionsWork.GARDEN_WORKER,
                    Priorities.MIDDLE,
                    LocalDate.of(2000, 10, 11),
                    OrderStatus.IN_PROCESS,
                    LocalDate.of(2010, 4, 14),
                    1000,
                    user(),
                    worker()
            );
            list.add(order);
        }
        return list;
    }

    public static WorkerEntity worker() {
        return new WorkerEntity("semen",
                "bublic",
                "haha@mail.ru",
                "jhoni",
                OptionsWork.GARDEN_WORKER);
    }

    public static Page<OrderEntity> page() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        return new PageImpl<>(orderEntitiesList().subList(0, 5),
                pageRequest,
                orderEntitiesList().size());
    }

    public static AdminDto adminDto() {
        return AdminDto.builder()
                .firstName("ivan")
                .secondName("kom")
                .mail("iv@mail.ru")
                .password("pas")
                .secretCompanyKey("11223344556677889900")
                .build();
    }

    public static OrderMasterDto orderMasterDto(String mailOfNewWorker, UUID id) {
        return OrderMasterDto.builder()
                .orderID(id)
                .mailOfNewWorker(mailOfNewWorker)
                .build();
    }
    public static OrderMasterDto orderMasterDto() {
        return orderMasterDto(user().getMail(), UUID.randomUUID());
    }

}
