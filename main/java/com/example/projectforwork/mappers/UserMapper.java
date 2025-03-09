package com.example.projectforwork.mappers;

import com.example.projectforwork.dto.UserDtoForRegistration;
import com.example.projectforwork.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserMapper {

    static UserEntity fromUserDtoToUserEntity(UserDtoForRegistration userDto, PasswordEncoder passwordEncoder) {
        return new UserEntity(
                userDto.getFirstName(),
                userDto.getSecondName(),
                userDto.getMail(),
                passwordEncoder.encode(userDto.getPassword())
        );
    }
}
