package com.example.projectforwork.mappers;

import com.example.projectforwork.dto.FullOrderDto;
import com.example.projectforwork.dto.WorkerDto;
import com.example.projectforwork.entity.OrderEntity;
import com.example.projectforwork.entity.WorkerEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface WorkerMapper {

    static WorkerEntity fromDtoToWorkerEntity(WorkerDto workerDto, PasswordEncoder passwordEncoder) {
        return new WorkerEntity(
                workerDto.getFirstName(),
                workerDto.getSecondName(),
                workerDto.getMail(),
                passwordEncoder.encode(workerDto.getPassword()),
                workerDto.getWorkType()
        );
    }
}
