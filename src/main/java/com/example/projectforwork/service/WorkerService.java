package com.example.projectforwork.service;

import com.example.projectforwork.dto.*;
import com.example.projectforwork.entity.CommentEntity;
import com.example.projectforwork.entity.OrderEntity;
import com.example.projectforwork.entity.WorkerEntity;
import com.example.projectforwork.enums.OrderStatus;
import com.example.projectforwork.enums.TypesRepairs;
import com.example.projectforwork.exceptions.OrderNotFoundException;
import com.example.projectforwork.exceptions.PageCantBeZeroException;
import com.example.projectforwork.exceptions.WorkerNotFoundException;
import com.example.projectforwork.mappers.CommentMapper;
import com.example.projectforwork.mappers.OrderMapper;
import com.example.projectforwork.mappers.WorkerMapper;
import com.example.projectforwork.repository.CommentRepository;
import com.example.projectforwork.repository.OrderRepository;
import com.example.projectforwork.repository.WorkerRepository;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkerService {
    private final WorkerRepository workerRepository;
    private final OrderRepository orderRepository;
    private final PasswordEncoder passwordEncoder;
    private final CommentRepository commentRepository;


    @Transactional
    public void postNewWorker(WorkerDto workerDto) {
        workerRepository.save(WorkerMapper.fromDtoToWorkerEntity(workerDto, passwordEncoder));
    }

    @Transactional
    public void setAvatarForWorker(Authentication authentication, MultipartFile multipartFile) throws IOException {
        WorkerEntity worker = workerRepository.findWorkerByEmail(authentication.getName()).orElseThrow(WorkerNotFoundException::new);
        worker.setPhoto(multipartFile.getBytes());
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public void takeOrder(Authentication authentication, UUID idOrder) {
        WorkerEntity worker = workerRepository.findWorkerByEmail(authentication.getName()).orElseThrow(WorkerNotFoundException::new);
        OrderEntity order = orderRepository.findById(idOrder).orElseThrow(OrderNotFoundException::new);
        assignWorker(order, worker);
    }

    private void assignWorker(OrderEntity order, WorkerEntity worker) {
        if(order.getOrderStatus().equals(OrderStatus.IN_PROCESS) || order.getOrderStatus().equals(OrderStatus.COMPLETED)){
            throw new OrderNotFoundException();
        }
        order.setWorker(worker);
        order.setOrderStatus(OrderStatus.IN_PROCESS);
        order.setInProcessSinceDate(LocalDate.now());
    }

    @Transactional(readOnly = true)
    public List<OrderDtoForWorker> getAllAvailableOrdersWithRequestedType(TypesRepairs typesRepairs, int page, int count) {
        check(page);
        PageRequest pageRequest = PageRequest.of(page - 1, count);
        Page<OrderEntity> entityPage = orderRepository.findAllByTypesRepairsAndOrderStatus(typesRepairs,OrderStatus.NOT_STARTED,  pageRequest);
        return entityPage.map(OrderMapper::fromEntityToOrderDtoForWorker)
                .stream().toList();
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public void refuseFromOrderForWorker(Authentication authentication,
                                         UUID orderId) {
        WorkerEntity worker = workerRepository.findWorkerByEmail(authentication.getName()).orElseThrow(WorkerNotFoundException::new);
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        refuseFromOrder(order, worker.getId());
    }

    private void refuseFromOrder(OrderEntity order, UUID workerID) {
        checkId(order, workerID);
        order.setInProcessSinceDate(null);
        order.setOrderStatus(OrderStatus.NOT_STARTED);
        order.setWorker(null);
    }

    @Transactional(readOnly = true)
    public byte[] getAvatar(Authentication authentication) {
        return workerRepository.findWorkerByEmail(authentication.getName()).orElseThrow(WorkerNotFoundException::new).getPhoto();
    }

    @Transactional()
    public void leaveCommentForOrder(Authentication authentication, UUID orderId, String comment) {

        WorkerEntity worker = workerRepository.findWorkerByEmail(authentication.getName()).orElseThrow(WorkerNotFoundException::new);
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        CommentEntity commentEntity = CommentMapper.createCommentEntity(worker, order, comment);
        commentRepository.save(commentEntity);
    }
   @Transactional
    public List<CommentDto> getAllCommentsByOrderId(UUID orderId, int page, int count) {
        PageRequest pageRequest = PageRequest.of(page - 1, count, Sort.by("id").descending());
       Page<CommentEntity> commentEntities = commentRepository.getByOrderId(orderId, pageRequest);
       return CommentMapper.fromCommentEntityPageToCommentDtoList(commentEntities);
   }

    private void check(int page) {
        if (page == 0) {
            throw new PageCantBeZeroException();
        }
    }

    private void checkId(OrderEntity order, UUID workerId) {
        if (order.getWorker() == null) {
            throw new IllegalArgumentException("this order doesnt belong to you");
        }
        if (!order.getWorker().getId().equals(workerId)) {
            throw new IllegalArgumentException("Incorrect arguments");
        }
    }
}
