package com.example.projectforwork.controller;

import com.example.projectforwork.dto.*;
import com.example.projectforwork.enums.TypesRepairs;
import com.example.projectforwork.service.WorkerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employee-service")
@RequiredArgsConstructor
public class WorkerController {
    private final WorkerService workerService;

    @Operation(summary = "Установка и загрузка аватара для работника")
    @ApiResponse(responseCode = "200", description = "Avatar is posted")
    @PostMapping(value = "/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> postAvatarForUser(Authentication authentication,
                                                    @RequestBody MultipartFile multipartFile) throws IOException {
        workerService.setAvatarForWorker(authentication, multipartFile);
        return ResponseEntity.ok("Avatar is posted");
    }

    @Operation(summary = "Мастер берет доступный заказ по айди")
    @ApiResponse(responseCode = "200")
    @PatchMapping("/order/{id}/take")
    public ResponseEntity<String> takeOrder(Authentication authentication,
                                            @PathVariable("id") UUID orderId) {
        workerService.takeOrder(authentication, orderId);
        return ResponseEntity.ok("Order is taken!");
    }

    @Operation(summary = "Получить загруженное фото для аккаунта")
    @ApiResponse(responseCode = "200")
    @GetMapping(value = "/avatar")
    public ResponseEntity<byte[]> getAvatar(Authentication authentication) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(workerService.getAvatar(authentication));
    }

    @GetMapping("/orders")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Получение всех доступных заказов определенной категории")
    public ResponseEntity<List<OrderDtoForWorker>> getAllAvailableOrdersWithType(@RequestParam("page") int page,
                                                                                 @RequestParam("count") int count,
                                                                                 @RequestParam("type") TypesRepairs typesRepairs) {
        return ResponseEntity.ok()
                .body(workerService.getAllAvailableOrdersWithRequestedType(typesRepairs, page, count));
    }

    @PatchMapping("/orders/{id}/refuse")
    @Operation(summary = "Отказ от заказа")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<String> refuseFromOrder(Authentication authentication,
                                                  @PathVariable("id") UUID orderId) {
        workerService.refuseFromOrderForWorker(authentication, orderId);
        return ResponseEntity.ok("you refused from order");
    }

    @Operation(summary = "Оставить комментарий к заказу")
    @ApiResponse(responseCode = "200")
    @PostMapping("/orders/{id}/comment")
    public ResponseEntity<String> leaveComment(Authentication authentication,
                                               @PathVariable("id") UUID orderId,
                                               @RequestParam("comment") String comment) {
        workerService.leaveCommentForOrder(authentication, orderId, comment);
        return ResponseEntity.ok("you left comment");
    }
    @Operation(summary = "Получить все коментарии к заказу с пагинацией, страничка-количество")
    @ApiResponse(responseCode = "200")
    @GetMapping("/orders/{id}/comments")
    public ResponseEntity<List<CommentDto>> getAllCommentsOfOrder(@PathVariable("id") UUID id,
                                                                  @RequestParam("page") int page,
                                                                  @RequestParam("count") int count) {
        return ResponseEntity.ok()
                .body(workerService.getAllCommentsByOrderId(id, page, count));
    }
}
