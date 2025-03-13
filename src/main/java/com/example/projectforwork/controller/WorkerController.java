package com.example.projectforwork.controller;

import com.example.projectforwork.dto.*;
import com.example.projectforwork.enums.TypesRepairs;
import com.example.projectforwork.service.WorkerService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/post-avatar-for-worker")
    public ResponseEntity<String> postAvatarForUser(Authentication authentication,
                                                    @RequestBody MultipartFile multipartFile) throws IOException {
        workerService.setAvatarForWorker(authentication, multipartFile);
        return ResponseEntity.ok("Avatar is posted");
    }

    @PostMapping("/take-order")
    public ResponseEntity<String> takeOrder(Authentication authentication,
                                            @RequestBody UUID orderId) {
        workerService.takeOrder(authentication, orderId);
        return ResponseEntity.ok("Order is taken!");
    }

    @GetMapping("/get-all-available-orders/{page}/{count}")
    public ResponseEntity<List<OrderDtoForWorker>> getAllAvailableOrdersWithType(@PathVariable("page") int page,
                                                                                 @PathVariable("count") int count,
                                                                                 @RequestParam("type") TypesRepairs typesRepairs) {
        return ResponseEntity.ok()
                .body(workerService.getAllAvailableOrdersWithRequestedType(typesRepairs, page, count));
    }

    @PatchMapping("/refuse-from-order")
    public ResponseEntity<String> refuseFromOrder(Authentication authentication,
                                                  @RequestBody UUID orderId) {
        workerService.refuseFromOrderForWorker(authentication, orderId);
        return ResponseEntity.ok("you refused from order");
    }

    @PostMapping("/leave-comment-for-order")
    public ResponseEntity<String> leaveComment(Authentication authentication,
                                               @RequestBody UUID orderId,
                                               @RequestParam("comment") String comment) {
        workerService.leaveCommentForOrder(authentication, orderId, comment);
        return ResponseEntity.ok("you left comment");
    }

    @PostMapping("/get-all-comments")
    public ResponseEntity<?> getAllComments(@RequestBody PageWithIdOrderDto page) {
        return ResponseEntity.ok()
                .body(workerService.getAllCommentsByOrderId(page));
    }
}
