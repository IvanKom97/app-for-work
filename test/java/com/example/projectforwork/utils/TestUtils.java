package com.example.projectforwork.utils;

import com.example.projectforwork.entity.AbstractUserEntity;
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

}
