package com.example.projectforwork.repository;


import com.example.projectforwork.dto.FullOrderDto;
import com.example.projectforwork.dto.OrderDtoForAdmin;
import com.example.projectforwork.entity.OrderEntity;
import com.example.projectforwork.enums.OrderStatus;
import com.example.projectforwork.enums.TypesRepairs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {


    Page<OrderEntity> findAllByTypesRepairsAndOrderStatus(TypesRepairs typesRepairs, OrderStatus orderStatus, Pageable pageable);

    Optional<OrderEntity> findByIdAndWorkerId(@Param("orderId") UUID orderId,
                                              @Param("workerId") UUID workerId);

    @Modifying
    @Query(value = "update OrderEntity o " +
            "set o.orderStatus = 'REFUSED'" +
            " where o.user.mail=:mail and o.id=:orderId")
    int deleteOrderByUser(@Param("mail") String mail,
                                             @Param("orderId") UUID orderId);

    Page<OrderEntity> findAllByUserMail(String username, Pageable pageable);
}
