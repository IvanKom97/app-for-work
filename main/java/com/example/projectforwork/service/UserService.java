package com.example.projectforwork.service;

import com.example.projectforwork.dto.UserDtoForRegistration;
import com.example.projectforwork.entity.UserEntity;
import com.example.projectforwork.mappers.UserMapper;
import com.example.projectforwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void postNewUser(UserDtoForRegistration userDto) {
        userRepository.save(UserMapper.fromUserDtoToUserEntity(userDto, passwordEncoder));
    }

    @Transactional()
    public void setAvatarForUser(Authentication authentication, MultipartFile multipartFile) throws IOException {
        UserEntity user = userRepository.findUserByEmail(authentication.getName()).orElseThrow();
        user.setPhoto(multipartFile.getBytes());
    }

}
