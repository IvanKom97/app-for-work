package com.example.projectforwork.controller;

import com.example.projectforwork.dto.UserDtoForRegistration;
import com.example.projectforwork.dto.WorkerDto;
import com.example.projectforwork.service.UserService;
import com.example.projectforwork.service.WorkerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final WorkerService workerService;

    @PostMapping("/new-user")
    public ResponseEntity<String> registrationUser(@RequestBody @Valid UserDtoForRegistration info) {
        userService.postNewUser(info);
        return ResponseEntity.ok("Registration is done");
    }

    @PostMapping("/post-worker")
    public ResponseEntity<?> registrationNewWorker(@RequestBody WorkerDto workerDto) {
        workerService.postNewWorker(workerDto);
        return ResponseEntity.ok("registration is done");
    }
}
