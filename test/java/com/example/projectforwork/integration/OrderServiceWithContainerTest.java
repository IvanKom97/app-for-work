package com.example.projectforwork.integration;

import com.example.projectforwork.dto.OrderDto;
import com.example.projectforwork.entity.OrderEntity;
import com.example.projectforwork.repository.OrderRepository;
import com.example.projectforwork.repository.UserRepository;
import com.example.projectforwork.service.OrderService;
import com.example.projectforwork.utils.AbstractTestContainer;
import com.example.projectforwork.utils.TestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.experimental.results.ResultMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class OrderServiceWithContainerTest extends AbstractTestContainer {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "ivan", roles = {"USER"})
    public void postOrderTest() throws Exception {
        OrderDto orderDto = TestUtils.orderDto();
        mockMvc.perform(MockMvcRequestBuilders.post("/order-service/post-order")
                        .content(objectMapper.writeValueAsString(orderDto))
                        .principal(() -> "ivan"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Order is posted"));
    }
}
