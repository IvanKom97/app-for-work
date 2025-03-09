package com.example.projectforwork.service;

import com.example.projectforwork.dto.OrderDto;
import com.example.projectforwork.dto.OrderDtoFullInfo;
import com.example.projectforwork.dto.UserDtoForRegistration;
import com.example.projectforwork.dto.WorkerDto;
import com.example.projectforwork.entity.OrderEntity;
import com.example.projectforwork.entity.UserEntity;
import com.example.projectforwork.entity.WorkerEntity;
import com.example.projectforwork.enums.OrderStatus;
import com.example.projectforwork.exceptions.OrderNotFoundException;
import com.example.projectforwork.exceptions.WorkerNotFoundException;
import com.example.projectforwork.mappers.UserMapper;
import com.example.projectforwork.mappers.WorkerMapper;
import com.example.projectforwork.repository.OrderRepository;
import com.example.projectforwork.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.annotation.Repeatable;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerService {
    private final WorkerRepository workerRepository;
    private final OrderRepository orderRepository;
    private final PasswordEncoder passwordEncoder;


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
    public void takeOrder(Authentication authentication, OrderDto orderDto) {
        OrderEntity order = orderRepository.findOrderByDescription(orderDto.getDescription()).orElseThrow(OrderNotFoundException::new);
        order.setWorker(workerRepository.findWorkerByEmail(authentication.getName()).orElseThrow(WorkerNotFoundException::new));
        order.setInProcessSinceDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.IN_PROCESS);
    }

    @Transactional(readOnly = true)
    public List<OrderDtoFullInfo> getAllAvailableOrders(Authentication authentication) {
        return null;
    }
}
