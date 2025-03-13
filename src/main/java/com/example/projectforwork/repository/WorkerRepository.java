package com.example.projectforwork.repository;


import com.example.projectforwork.entity.WorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;


public interface WorkerRepository extends JpaRepository<WorkerEntity, UUID> {
    @Query(value = "from WorkerEntity w where w.mail=:mail")
    Optional<WorkerEntity> findWorkerByEmail(@Param("mail") String mail);
}
