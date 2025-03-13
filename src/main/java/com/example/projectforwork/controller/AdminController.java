package com.example.projectforwork.controller;

import com.example.projectforwork.dto.*;
import com.example.projectforwork.service.AdminService;
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

    @DeleteMapping("/delete-order")
    public ResponseEntity<String> deleteOrderById(@RequestBody UUID id) {
        adminService.deleteOrder(id);
        return ResponseEntity.ok("Deleted successful");
    }

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserDtoForRegistration userDto) {
        adminService.createUSer(userDto);
        return ResponseEntity.ok("user created successful");
    }


    @PostMapping("/create-order-with-user")
    public ResponseEntity<String> createOrderWithUser(@RequestBody @Valid JoinedDtoUserOrder joinedDtoUserOrder) {
        adminService.createUserWithOrder(joinedDtoUserOrder.getUserDtoForRegistration(), joinedDtoUserOrder.getOrderDto());
        return ResponseEntity.ok("user and order created successful");
    }

    @PatchMapping("/set-new-worker")
    public ResponseEntity<String> setNewWorker(@RequestBody @Valid OrderMasterDto orderDto) {
        adminService.setNewMaster(orderDto);
        return ResponseEntity.ok("Set new master successful");
    }

    @GetMapping("/get-info-about-orders-pages/{page}/{size}")
    public ResponseEntity<List<FullOrderDto>> getInfoAboutOrdersByDto(@PathVariable("page") int page,
                                                                      @PathVariable("size") int size) {
        return ResponseEntity.ok()
                .body(adminService.getInfoAboutOrdersPages(page, size));
    }

    @GetMapping("/get-info-about-user-by-id")
    public ResponseEntity<FullOrderDto> getInfoAboutOrderById(@RequestBody UUID orderId) {
        return ResponseEntity.ok()
                .body(adminService.getInfoAboutOrderById(orderId));
    }

    @PatchMapping("/set-new-priority")
    public ResponseEntity<String> setNewPriority(@RequestBody OrderIdPriorityDto orderIdPriorityDto) {
        adminService.setNewPriority(orderIdPriorityDto);
        return ResponseEntity.ok("Set new priority successful");
    }

    @PostMapping("/get-info-user-orders")
    public ResponseEntity<UserDtoForAdmin> getInfoUserOrders(@RequestBody PageDto pageDto) {
        return ResponseEntity.ok().body(adminService.getInfoAboutOrdersOfUserByPages(pageDto));
    }

    @PostMapping("/leave-comment-for-order")
    public ResponseEntity<String> leaveComment(Authentication authentication,
                                               @RequestBody UUID orderId,
                                               @RequestParam ("comment") String comment ) {
        adminService.leaveCommentForOrder(authentication, orderId, comment);
        return ResponseEntity.ok("You left comment");
    }

    @PostMapping("/get-all-comments-of-order")
    public ResponseEntity<List<CommentDto>> getAllCommentsByOrderId(@RequestBody PageWithIdOrderDto  page) {
        return ResponseEntity.ok().body(adminService.getAllCommentsByOrderId(page));
    }

    @PostMapping("/create-order-for-user")
    public ResponseEntity<String> createOrderForUser(@RequestBody UserForCreatingOrderDto userForCreatingOrderDto) {
        adminService.createNewOrderForUser(userForCreatingOrderDto);
        return ResponseEntity.ok("order created");
    }

}
