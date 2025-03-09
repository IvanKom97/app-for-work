package com.example.projectforwork.utils;

import com.example.projectforwork.dto.OrderDto;
import com.example.projectforwork.dto.UserDtoForRegistration;
import com.example.projectforwork.entity.AbstractUserEntity;
import com.example.projectforwork.entity.OrderEntity;
import com.example.projectforwork.entity.UserEntity;
import com.example.projectforwork.enums.Roles;

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
                .description("поспать")
                .build();
    }

    public static OrderEntity orderEntity() {
        return OrderEntity.builder()
                .descriptions("haha")
                .user(user())
                .build();
    }
}
