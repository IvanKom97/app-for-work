package com.example.projectforwork.mappers;

import com.example.projectforwork.dto.AdminDto;
import com.example.projectforwork.entity.AdminEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface AdminMapper {

    static AdminEntity fromDtoTOAdminEntity(AdminDto adminDto, PasswordEncoder passwordEncoder) {
        return new AdminEntity(
                adminDto.getFirstName(),
                adminDto.getSecondName(),
                adminDto.getMail(),
                passwordEncoder.encode(adminDto.getPassword())
        );
    }
}
