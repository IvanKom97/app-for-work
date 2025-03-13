package com.example.projectforwork.service;

import com.example.projectforwork.dto.*;
import com.example.projectforwork.entity.*;
import com.example.projectforwork.enums.OrderStatus;
import com.example.projectforwork.enums.Priorities;
import com.example.projectforwork.exceptions.*;
import com.example.projectforwork.mappers.AdminMapper;
import com.example.projectforwork.mappers.CommentMapper;
import com.example.projectforwork.mappers.OrderMapper;
import com.example.projectforwork.mappers.UserMapper;
import com.example.projectforwork.repository.*;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final WorkerRepository workerRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    @Value("${secret}")
    private String secretik;
    private final PasswordEncoder passwordEncoder;
    private final CommentRepository commentRepository;

    @Transactional
    public void adminRegistration(AdminDto adminDto) {
        if (adminDto.getSecretCompanyKey().equals(secretik)) {
            AdminEntity admin = AdminMapper.fromDtoTOAdminEntity(adminDto, passwordEncoder);
            adminRepository.save(admin);
        } else throw new ForrbidenByCompanyException();
    }

    @Transactional()
    public void deleteOrder(UUID id) {
        OrderEntity order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        orderRepository.delete(order);
        //сделал через поиск,
        // так как такого заказа может не быть,
        // не протестировать, был ли такой заказ с айди
    }

    @Transactional()
    public void createUSer(UserDtoForRegistration userDto) {
        UserEntity user = UserMapper.fromUserDtoToUserEntity(userDto, passwordEncoder);
        userRepository.save(user);
    }

    @Transactional
    public void createUserWithOrder(UserDtoForRegistration userDtoForRegistration,
                                    OrderDto orderDto) {
        UserEntity user = UserMapper.fromUserDtoToUserEntity(userDtoForRegistration, passwordEncoder);
        OrderEntity order = OrderMapper.fromOrderDtoToOrderEntity(orderDto, user);
        userRepository.save(user);
        orderRepository.save(order);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Retryable(maxAttempts = 5,
            backoff = @Backoff(value = 1500))
    public void setNewMaster(OrderMasterDto orderMasterDto) {
        WorkerEntity worker = workerRepository.findWorkerByEmail(orderMasterDto.getMailOfNewWorker()).orElseThrow(WorkerNotFoundException::new);
        OrderEntity order = orderRepository.findById(orderMasterDto.getOrderID()).orElseThrow(OrderNotFoundException::new);
        order.setWorker(worker);
        order.setOrderStatus(OrderStatus.IN_PROCESS);
        order.setInProcessSinceDate(LocalDate.now());
    }

    @Transactional(readOnly = true)
    public List<FullOrderDto> getInfoAboutOrdersPages(int page, int requestedCount) {
        checkPage(page);
        PageRequest pageRequest = PageRequest.of(page - 1, requestedCount);
        return orderRepository.findAll(pageRequest)
                .map(OrderMapper::fromEntityToFullOrderDto)
                .stream()
                .toList();
    }

    @Transactional(readOnly = true)
    public FullOrderDto getInfoAboutOrderById(UUID orderId) {
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        return OrderMapper.fromEntityToFullOrderDto(order);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void setNewPriority(OrderIdPriorityDto idPriorityDto) {
        OrderEntity order = orderRepository.findById(idPriorityDto.getOrderId()).orElseThrow(OrderNotFoundException::new);
        order.setPriorities(idPriorityDto.getPriorities());
    }

    @Transactional(readOnly = true)
    public UserDtoForAdmin getInfoAboutOrdersOfUserByPages(PageDto pageDto) {
        checkPage(pageDto);
        UserEntity user = userRepository.findUserByEmail(pageDto.getMail()).orElseThrow(UserNotFoundException::new);
        PageRequest pageRequest = PageRequest.of(pageDto.getPage() - 1, pageDto.getRequestedCount());
        List<OrderDtoForAdmin> list = OrderMapper.fromPageToListOrderDtoForAdmin(orderRepository.findAllByUserMail(pageDto.getMail(), pageRequest));
        return UserMapper.fromEntityToUserDtoForMapper(user, list);
    }
    @Transactional
    public void leaveCommentForOrder(Authentication authentication, UUID orderId,  String comment) {
        AdminEntity admin = adminRepository.findAdminByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new);
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        CommentEntity com = CommentMapper.createCommentEntity(admin, order, comment);
        commentRepository.save(com);
    }

    @Transactional
    public List<CommentDto> getAllCommentsByOrderId(PageWithIdOrderDto orderDto) {
        checkPage(orderDto.getPage());
        PageRequest pageRequest = PageRequest.of(orderDto.getPage() - 1, orderDto.getCount());
        Page<CommentEntity> commentEntities = commentRepository.getByOrder_Id(orderDto.getOrderId(), pageRequest);
        return CommentMapper.fromCommentEntityPageToCommentDtoList(commentEntities);
    }

    @Transactional
    public void createNewOrderForUser(UserForCreatingOrderDto dto) {
        UserEntity user = userRepository.findUserByEmail(dto.getMail()).orElseThrow(UserNotFoundException::new);
        OrderEntity order = OrderMapper.fromOrderDtoToOrderEntity(dto.getOrderDto(), user);
        orderRepository.save(order);
    }

    private void checkPage(PageDto pageDto) {
        if (pageDto.getPage() == 0) {
            System.out.println("uuu");
            throw new PageCantBeZeroException();
        }
    }

    private void checkPage(int page) {
        if (page == 0) {
            throw new PageCantBeZeroException();
        }
    }
}

