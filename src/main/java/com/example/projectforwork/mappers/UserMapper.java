package com.example.projectforwork.mappers;

import com.example.projectforwork.dto.OrderDtoForAdmin;
import com.example.projectforwork.dto.UserDtoForAdmin;
import com.example.projectforwork.dto.UserDtoForRegistration;
import com.example.projectforwork.entity.OrderEntity;
import com.example.projectforwork.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface UserMapper {

    static UserEntity fromUserDtoToUserEntity(UserDtoForRegistration userDto, PasswordEncoder passwordEncoder) {
        return new UserEntity(
                userDto.getFirstName(),
                userDto.getSecondName(),
                userDto.getMail(),
                passwordEncoder.encode(userDto.getPassword())
        );
    }

    static UserDtoForAdmin fromEntityToUserDtoForMapper(UserEntity user,
                                                        List<OrderDtoForAdmin> list) {
        return UserDtoForAdmin.builder()
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .mail(user.getMail())
                .orders(list)
                .build();
    }
}
