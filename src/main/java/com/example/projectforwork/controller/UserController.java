package com.example.projectforwork.controller;

import com.example.projectforwork.dto.OrderDto;
import com.example.projectforwork.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service")
public class UserController {
    private final UserService userService;


    @PostMapping(value = "/set-photo-for-user",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> setPhoto(Authentication authentication,
                                           @RequestBody MultipartFile multipartFile) throws IOException {
        userService.setAvatarForUser(authentication, multipartFile);
        return ResponseEntity.ok("Avatar is loaded");
    }
    @PostMapping("/post-order")
    public ResponseEntity<String> postOrder(Authentication authentication,
                                            @RequestBody @Valid OrderDto orderDto) {
        userService.postOrder(authentication, orderDto);
        return ResponseEntity.ok("Order is posted");
    }
}
