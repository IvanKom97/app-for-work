package com.example.projectforwork.utils;

import com.example.projectforwork.entity.OrderEntity;
import com.example.projectforwork.entity.UserEntity;
import com.example.projectforwork.repository.AdminRepository;
import com.example.projectforwork.repository.OrderRepository;
import com.example.projectforwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;

public interface SaveUtilsTest {


    static UserEntity saveUser(UserRepository userRepository) {
        UserEntity user = TestUtils.user();
        return userRepository.save(user);
    }

    static void saveOrder(OrderRepository orderRepository, int times, UserEntity user) {
        for (int i = 0; i < times; i++) {
            OrderEntity order = TestUtils.orderEntity(user);
            orderRepository.save(order);
        }
    }
}
