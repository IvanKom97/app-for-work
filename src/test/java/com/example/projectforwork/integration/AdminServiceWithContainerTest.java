package com.example.projectforwork.integration;

import com.example.projectforwork.dto.AdminDto;
import com.example.projectforwork.dto.OrderMasterDto;
import com.example.projectforwork.dto.PageDto;
import com.example.projectforwork.dto.UserDtoForAdmin;
import com.example.projectforwork.entity.AdminEntity;
import com.example.projectforwork.entity.OrderEntity;
import com.example.projectforwork.entity.UserEntity;
import com.example.projectforwork.entity.WorkerEntity;
import com.example.projectforwork.enums.OptionsWork;
import com.example.projectforwork.enums.OrderStatus;
import com.example.projectforwork.exceptions.ForrbidenByCompanyException;
import com.example.projectforwork.exceptions.OrderNotFoundException;
import com.example.projectforwork.repository.AdminRepository;
import com.example.projectforwork.repository.OrderRepository;
import com.example.projectforwork.repository.UserRepository;
import com.example.projectforwork.repository.WorkerRepository;
import com.example.projectforwork.service.AdminService;
import com.example.projectforwork.utils.AbstractTestContainer;
import com.example.projectforwork.utils.SaveUtilsTest;
import com.example.projectforwork.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AdminServiceWithContainerTest extends AbstractTestContainer {
    @Autowired
    private  AdminRepository adminRepository;
    @Autowired
    private  WorkerRepository workerRepository;
    @Autowired
    private  OrderRepository orderRepository;
    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private AdminService adminService;

    @Test
    public void adminRegistrationTest() {
        AdminDto adminDto = TestUtils.adminDto();
        adminService.adminRegistration(adminDto);
        assertEquals(adminRepository.findAll().size(), 1);
        AdminEntity savedAdmin = adminRepository.findAll().get(0);
        assertEquals(adminDto.getMail(), savedAdmin.getMail());
    }
    @Test
    public void adminRegistrationExceptionTest() {
        AdminDto adminDto = TestUtils.adminDto();
        adminDto.setSecretCompanyKey("123");
        assertThrows(ForrbidenByCompanyException.class,
                () -> adminService.adminRegistration(adminDto));
    }

    @Test
    public void deleteOrderExceptionTest() {
        UUID id = UUID.randomUUID();
        assertThrows(OrderNotFoundException.class,
                () -> adminService.deleteOrder(id));
    }

    @Test
    public void deleteOrderTest() {
        UserEntity user = TestUtils.user();
        userRepository.save(user);
        OrderEntity order = TestUtils.orderEntity(user);
        orderRepository.save(order);
        adminService.deleteOrder(order.getId());
        assertEquals(orderRepository.findAll().size(), 0);
    }

    @Test
    public void setNewMasterTest() {
        UserEntity user = TestUtils.user();
        OrderEntity order = TestUtils.orderEntity(user);
        WorkerEntity worker = new WorkerEntity("Ivan",
                "Komov",
                 "pes@maail.ru",
                "gav",
                OptionsWork.GARDEN_WORKER);
        workerRepository.save(worker);
        userRepository.save(user);
        orderRepository.save(order);
        System.out.println(order.getId());
        OrderMasterDto orderMasterDto = TestUtils.orderMasterDto(worker.getMail(), order.getId());
        adminService.setNewMaster(orderMasterDto);
        assertEquals(order.getWorker(), worker);
        assertEquals(order.getOrderStatus(), OrderStatus.IN_PROCESS);
    }

    @Test
    void getAllByUserMailPageTest() {
        UserEntity user = SaveUtilsTest.saveUser(userRepository);
        String username = user.getMail();
        SaveUtilsTest.saveOrder(orderRepository, 10, user);
        PageDto pageDto = new PageDto(username, 1, 5);
        UserDtoForAdmin userDtoForAdmin = adminService.getInfoAboutOrdersOfUserByPages(pageDto);
        assertEquals(userDtoForAdmin.getOrders().size(), 5);
        assertEquals(userDtoForAdmin.getMail(), user.getMail());

    }
}
