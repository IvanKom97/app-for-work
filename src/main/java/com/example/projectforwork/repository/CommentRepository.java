package com.example.projectforwork.repository;

import com.example.projectforwork.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {

    Page<CommentEntity> getByOrderId(UUID orderId, Pageable pageable);
}
