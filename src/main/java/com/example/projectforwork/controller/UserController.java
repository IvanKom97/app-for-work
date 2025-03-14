package com.example.projectforwork.controller;

import com.example.projectforwork.dto.OrderDto;
import com.example.projectforwork.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service")
public class UserController {
    private final UserService userService;
    @Operation(summary = "Установить новый аватар")
    @ApiResponse(responseCode = "200")
    @PostMapping(value = "/avatar",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> setPhoto(Authentication authentication,
                                           @RequestBody MultipartFile multipartFile) throws IOException {
        userService.setAvatarForUser(authentication, multipartFile);
        return ResponseEntity.ok("Avatar is loaded");
    }

    @Operation(summary = "Публикация заказа")
    @ApiResponse(responseCode = "200",
            content = @Content(schema = @Schema(implementation = OrderDto.class)))
    @PostMapping("/orders")
    public ResponseEntity<String> postOrder(Authentication authentication,
                                            @RequestBody @Valid OrderDto orderDto) {
        userService.postOrder(authentication, orderDto);
        return ResponseEntity.ok("Order is posted");
    }

    @DeleteMapping("/orders/{id}")
    @Operation(summary = "Удаляет заказ по айди")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<?> deleteOrder(Authentication authentication,
                                         @PathVariable("id") UUID orderId) {
        userService.deleteOrder(authentication, orderId);
        return ResponseEntity.ok("Order is deleted");
    }

    @GetMapping("/avatar")
    @Operation(summary = "Получение аватара по аутентификации у юзера")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<byte[]> getAvatar(Authentication authentication) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(userService.getAvatar(authentication));
    }
}
