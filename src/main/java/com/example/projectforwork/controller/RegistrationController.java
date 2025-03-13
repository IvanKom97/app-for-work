package com.example.projectforwork.controller;

import com.example.projectforwork.dto.AdminDto;
import com.example.projectforwork.dto.UserDtoForRegistration;
import com.example.projectforwork.dto.WorkerDto;
import com.example.projectforwork.service.AdminService;
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
    private final AdminService adminService;

    @PostMapping("/new-user")
    public ResponseEntity<String> registrationUser(@Valid @RequestBody UserDtoForRegistration info) {
        userService.postNewUser(info);
        return ResponseEntity.ok("Registration is done");
    }

    @PostMapping("/new-worker")
    public ResponseEntity<String> registrationNewWorker(@RequestBody @Valid WorkerDto workerDto) {
        workerService.postNewWorker(workerDto);
        return ResponseEntity.ok("registration is done");
    }

    @PostMapping("/new-admin")
    public ResponseEntity<String> registrationAdmin(@RequestBody @Valid AdminDto adminDto) {
        adminService.adminRegistration(adminDto);
        return ResponseEntity.ok("Registration is done");
    }
}
