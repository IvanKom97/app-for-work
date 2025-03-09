package com.example.projectforwork.unit;

import com.example.projectforwork.dto.OrderDto;
import com.example.projectforwork.entity.OrderEntity;
import com.example.projectforwork.entity.UserEntity;
import com.example.projectforwork.mappers.OrderMapper;
import com.example.projectforwork.repository.OrderRepository;
import com.example.projectforwork.repository.UserRepository;
import com.example.projectforwork.service.OrderService;
import com.example.projectforwork.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderService orderService;

    @Test
    public void postOrder() {
        String username = "username";
        Authentication authentication = Mockito.mock(Authentication.class);
        UserEntity user = TestUtils.user();
        OrderDto orderDto = TestUtils.orderDto();
        OrderEntity order = OrderMapper.fromOrderDtoToOrderEntity(orderDto, user);
        when(authentication.getName()).thenReturn(username);
        when(userRepository.findUserByEmail(username)).thenReturn(Optional.of(user));
        when(orderRepository.save(OrderMapper.fromOrderDtoToOrderEntity(orderDto, user))).thenReturn(order);
        orderService.postOrder(authentication, orderDto);
        verify(authentication, times(1)).getName();
        verify(userRepository, times(1)).findUserByEmail(username);
        verify(orderRepository, times(1)).save(OrderMapper.fromOrderDtoToOrderEntity(orderDto, user));
    }
}
