package com.example.projectforwork.controller;

import com.example.projectforwork.dto.AdminDto;
import com.example.projectforwork.dto.UserDtoForRegistration;
import com.example.projectforwork.dto.WorkerDto;
import com.example.projectforwork.service.AdminService;
import com.example.projectforwork.service.UserService;
import com.example.projectforwork.service.WorkerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Регистрация нового юзера")
    @ApiResponse(responseCode = "200",
    content = @Content(schema = @Schema(implementation = UserDtoForRegistration.class)))
    @PostMapping("/users")
    public ResponseEntity<String> registrationUser(@Valid @RequestBody UserDtoForRegistration info) {
        userService.postNewUser(info);
        return ResponseEntity.ok("Registration is done");
    }

    @Operation(summary = "Регистрация нового работника")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = WorkerDto.class)))
    @PostMapping("/workers")
    public ResponseEntity<String> registrationNewWorker(@RequestBody @Valid WorkerDto workerDto) {
        workerService.postNewWorker(workerDto);
        return ResponseEntity.ok("registration is done");
    }

    @Operation(summary = "Регистрация нового админа")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = AdminDto.class)))
    @PostMapping("/admins")
    public ResponseEntity<String> registrationAdmin(@RequestBody @Valid AdminDto adminDto) {
        adminService.adminRegistration(adminDto);
        return ResponseEntity.ok("Registration is done");
    }
}
