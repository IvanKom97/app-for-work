package com.example.projectforwork.unit;

import com.example.projectforwork.dto.OrderMasterDto;
import com.example.projectforwork.entity.OrderEntity;
import com.example.projectforwork.entity.UserEntity;
import com.example.projectforwork.entity.WorkerEntity;
import com.example.projectforwork.enums.OrderStatus;
import com.example.projectforwork.repository.OrderRepository;
import com.example.projectforwork.repository.WorkerRepository;
import com.example.projectforwork.service.AdminService;
import com.example.projectforwork.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private WorkerRepository workerRepository;
    @InjectMocks
    private AdminService adminService;



    @Test
    public void changeMasterTest() {
        UserEntity user = TestUtils.user();
        OrderMasterDto masterDto = TestUtils.orderMasterDto();
        WorkerEntity worker = TestUtils.worker();
        OrderEntity order = TestUtils.orderEntity(user);
        when(workerRepository.findWorkerByEmail(masterDto.getMailOfNewWorker())).thenReturn(Optional.of(worker));
        when(orderRepository.findById(masterDto.getOrderID())).thenReturn(Optional.of(order));
        adminService.setNewMaster(masterDto);
        assertEquals(order.getOrderStatus(), OrderStatus.IN_PROCESS);
        assertEquals(order.getWorker(), worker);
        verify(workerRepository, times(1)).findWorkerByEmail(masterDto.getMailOfNewWorker());
        verify(orderRepository, times(1)).findById(masterDto.getOrderID());
    }

}
