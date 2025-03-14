package com.example.projectforwork.service;

import com.example.projectforwork.dto.OrderDto;
import com.example.projectforwork.dto.UserDtoForRegistration;
import com.example.projectforwork.entity.OrderEntity;
import com.example.projectforwork.entity.UserEntity;
import com.example.projectforwork.exceptions.OrderNotFoundException;
import com.example.projectforwork.exceptions.UserNotFoundException;
import com.example.projectforwork.mappers.OrderMapper;
import com.example.projectforwork.mappers.UserMapper;
import com.example.projectforwork.repository.OrderRepository;
import com.example.projectforwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository orderRepository;

    @Transactional
    public void postNewUser(UserDtoForRegistration userDto) {
        userRepository.save(UserMapper.fromUserDtoToUserEntity(userDto, passwordEncoder));
    }

    @Transactional()
    public void setAvatarForUser(Authentication authentication, MultipartFile multipartFile) throws IOException {
        UserEntity user = userRepository.findUserByEmail(authentication.getName()).orElseThrow();
        user.setPhoto(multipartFile.getBytes());
    }

    @Transactional
    public void postOrder(Authentication authentication, OrderDto orderDto) {
        OrderEntity order = OrderMapper.fromOrderDtoToOrderEntity(orderDto, userRepository.findUserByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new));
        orderRepository.save(order);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteOrder(Authentication authentication, UUID orderId) {
        UserEntity user = userRepository.findUserByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new);
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        if (user.getId().equals(order.getUser().getId())) {
            orderRepository.deleteById(orderId);
        }
    }

    @Transactional(readOnly = true)
    public byte[] getAvatar(Authentication authentication) {
        return userRepository.findUserByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new).getPhoto();
    }
}
