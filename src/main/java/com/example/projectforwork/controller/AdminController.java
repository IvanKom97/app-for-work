package com.example.projectforwork.controller;

import com.example.projectforwork.dto.*;
import com.example.projectforwork.enums.Priorities;
import com.example.projectforwork.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/admin-service")
@RequiredArgsConstructor
@RestController
public class AdminController {

    private final AdminService adminService;

    @Operation(summary = "Создание нового пользователя")
    @ApiResponse(responseCode = "200",
            description = "user created successful",
            content = @Content(schema = @Schema(implementation = UserDtoForRegistration.class)))
    @PostMapping("/users/new")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserDtoForRegistration userDto) {
        adminService.createUSer(userDto);
        return ResponseEntity.ok("user created successful");
    }
    @Operation(summary = "Создание нового пользователя с заказом одновременно")
            @ApiResponse(responseCode = "200",
                    description = "user and order created successful",
            content = @Content(schema = @Schema(implementation = JoinedDtoUserOrder.class)))
    @PostMapping("/users/new-with-order")
    public ResponseEntity<String> createOrderWithUser(@RequestBody @Valid JoinedDtoUserOrder joinedDtoUserOrder) {
        adminService.createUserWithOrder(joinedDtoUserOrder.getUserDtoForRegistration(), joinedDtoUserOrder.getOrderDto());
        return ResponseEntity.ok("user and order created successful");
    }
    @Operation(summary = "Получение всех заказов определенного пользователя, с пагинацией")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")})
    @PostMapping("/users/{id}/orders")
    public ResponseEntity<UserDtoForAdmin> getAllOrdersOfSomeUser(@PathVariable("id") UUID id,
                                                                  @RequestParam ("page") int page,
                                                                  @RequestParam("count") int count) {
        return ResponseEntity.ok().body(adminService.getAllOrdersOfSomeUser(id, page, count));
    }

    @Operation(summary = "Удаление заказа по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Deleted successful"),
    @ApiResponse(responseCode = "404",
    description = "order doesnt exist")})
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable("id") UUID id) {
        adminService.deleteOrderById(id);
        return ResponseEntity.ok("Deleted successful");
    }

    @Operation(summary = "Назначение нового мастера на определенный заказ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Set new master successful",
            content = @Content(schema = @Schema(implementation = OrderMasterDto.class))),
    @ApiResponse(responseCode = "404",
            description = "worker || order doesnt exist")})
    @PatchMapping("/orders/assign-worker")
    public ResponseEntity<String> setNewWorker(@RequestBody @Valid OrderMasterDto orderDto) {
        adminService.setNewMaster(orderDto);
        return ResponseEntity.ok("Set new master successful");
    }

    @Operation(summary = "Получение всех заказов по заданным параметрам - страничка, кол-во элементов")
            @ApiResponse(responseCode = "200")
    @GetMapping("/orders")
    public ResponseEntity<List<FullOrderDto>> getAllOrdersFullInfo(@RequestParam int page,
                                                                      @RequestParam int size) {
        return ResponseEntity.ok()
                .body(adminService.getInfoAboutOrdersPages(page, size));
    }


    @Operation(summary = "Получение полной информации о заказе," +
            " включая данные о пользователе и работнике, " +
            "если заказ находится на стадии реализации")
            @ApiResponses({
                    @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404",
            description = "order doesnt exist")})
    @GetMapping("/orders/{id}")
    public ResponseEntity<FullOrderDto> getInfoAboutOrderById(@PathVariable("id") UUID orderId) {
        return ResponseEntity.ok()
                .body(adminService.getInfoAboutOrderById(orderId));
    }

    @Operation(summary = "Установления нового приоритета для задачи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Set new priority successful"),
    @ApiResponse(responseCode = "404", description = "order doesnt exist")})
    @PatchMapping("/orders/{id}/priority")
    public ResponseEntity<String> setNewPriority(@PathVariable("id") UUID orderId,
                                                 @RequestParam Priorities priority) {
        adminService.setNewPriority(orderId, priority);
        return ResponseEntity.ok("Set new priority successful");
    }
    @Operation(summary = "Эндпоинт для установки комментария к заказу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "You left comment"),
            @ApiResponse(responseCode = "404", description = "order doesnt exist")})
    @PostMapping("/orders/{id}/comment")
    public ResponseEntity<String> leaveComment(Authentication authentication,
                                               @PathVariable ("id") UUID orderId,
                                               @RequestParam ("comment") String comment ) {
        adminService.leaveCommentForOrder(authentication, orderId, comment);
        return ResponseEntity.ok("You left comment");
    }
    @Operation(summary = "Получение всех коментариев к заказу по айди")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "order doesnt exist")})
    @GetMapping("/orders/{id}/comment")
    public ResponseEntity<List<CommentDto>> getAllCommentsByOrderId(@PathVariable("id") UUID orderId,
                                                                    @RequestParam("page") int page,
                                                                    @RequestParam("count") int count) {
        return ResponseEntity.ok().body(adminService.getAllCommentsByOrderId(orderId, page, count));
    }
    @Operation(summary = "Создание заказа для уже существующего пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "order created",
            content = @Content(schema = @Schema(implementation = UserForCreatingOrderDto.class))),
            @ApiResponse(responseCode = "404", description = "user doesnt exist")})
    @PostMapping("/orders/for-user")
    public ResponseEntity<String> createOrderForUser(@RequestBody UserForCreatingOrderDto userForCreatingOrderDto) {
        adminService.createNewOrderForExistingUser(userForCreatingOrderDto);
        return ResponseEntity.ok("order created");
    }
}
