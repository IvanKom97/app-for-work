package com.example.projectforwork.unit;

import com.example.projectforwork.dto.UserDtoForRegistration;
import com.example.projectforwork.entity.UserEntity;
import com.example.projectforwork.mappers.UserMapper;
import com.example.projectforwork.repository.UserRepository;
import com.example.projectforwork.service.UserService;
import com.example.projectforwork.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    @Test
    public void postNewUserTest() {
        UserDtoForRegistration userDto = TestUtils.userDtoForRegistration();
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("12345");
        when(userRepository.save(UserMapper.fromUserDtoToUserEntity(userDto, passwordEncoder))).thenReturn(new UserEntity());
        userService.postNewUser(userDto);
        verify(userRepository, times(1)).save(UserMapper.fromUserDtoToUserEntity(userDto, passwordEncoder));
    }
}
