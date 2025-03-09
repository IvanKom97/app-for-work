package com.example.projectforwork.repository;


import com.example.projectforwork.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    @Query(value = "from OrderEntity o where o.descriptions=:description")
    Optional<OrderEntity> findOrderByDescription(@Param("description") String description);
}
