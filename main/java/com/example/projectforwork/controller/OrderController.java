package com.example.projectforwork.controller;

import com.example.projectforwork.dto.OrderDto;
import com.example.projectforwork.repository.OrderRepository;
import com.example.projectforwork.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/post-order")
    @Secured(value = "ROLE_USER")
    public ResponseEntity<String> postOrder(Authentication authentication,
                                            OrderDto orderDto) {
        orderService.postOrder(authentication, orderDto);
        return ResponseEntity.ok("Order is posted");
    }

    @DeleteMapping("/delete-order")
    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<String> deleteOrder(@RequestParam("description") String description) {
        orderService.deleteOrder(description);
        return ResponseEntity.ok("Order is deleted");
    }
}
