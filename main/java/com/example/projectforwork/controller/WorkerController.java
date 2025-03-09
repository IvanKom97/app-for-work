package com.example.projectforwork.controller;

import com.example.projectforwork.dto.OrderDto;
import com.example.projectforwork.dto.WorkerDto;
import com.example.projectforwork.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/employee-service")
@RequiredArgsConstructor
public class WorkerController {
    private final WorkerService workerService;

    @PostMapping("/post-avatar-for-worker")
    public ResponseEntity<String> postAvatarForUser(Authentication authentication,
                                                    @RequestBody MultipartFile multipartFile) throws IOException {
        workerService.setAvatarForWorker(authentication, multipartFile);
        return ResponseEntity.ok("Avatar is posted");
    }

    @PostMapping("/take-order")
    public ResponseEntity<String> takeOrder(Authentication authentication, OrderDto orderDto) {
        workerService.takeOrder(authentication, orderDto);
        return ResponseEntity.ok("Order is taken!");
    }

}
