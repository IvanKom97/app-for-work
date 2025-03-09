package com.example.projectforwork.service;

import com.example.projectforwork.dto.OrderDto;
import com.example.projectforwork.entity.OrderEntity;
import com.example.projectforwork.entity.UserEntity;
import com.example.projectforwork.exceptions.OrderNotFoundException;
import com.example.projectforwork.exceptions.UserNotFoundException;
import com.example.projectforwork.mappers.OrderMapper;
import com.example.projectforwork.repository.OrderRepository;
import com.example.projectforwork.repository.UserRepository;
import com.example.projectforwork.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
   private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Transactional
    public void postOrder(Authentication authentication, OrderDto orderDto) {
        OrderEntity order = OrderMapper.fromOrderDtoToOrderEntity(orderDto, userRepository.findUserByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new));
        orderRepository.save(order);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void deleteOrder(String description) {
        OrderEntity order = orderRepository.findOrderByDescription(description).orElseThrow(OrderNotFoundException::new);
        orderRepository.delete(order);
    }
}
