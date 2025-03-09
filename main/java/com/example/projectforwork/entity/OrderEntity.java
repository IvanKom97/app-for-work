package com.example.projectforwork.entity;

import com.example.projectforwork.dto.OrderDto;
import com.example.projectforwork.enums.OrderStatus;
import com.example.projectforwork.enums.Priorities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String descriptions;
    @Enumerated(value = EnumType.STRING)
    private Priorities priorities;
    private LocalDate publicationDate;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;
    @Column(name = "in_process_since_date")
    private LocalDate inProcessSinceDate;

    @Builder
    public OrderEntity(String descriptions, UserEntity user) {
        this.descriptions = descriptions;
        this.user = user;
        this.priorities = Priorities.MIDDLE;
        this.publicationDate = LocalDate.now();
        this.orderStatus = OrderStatus.NOT_STARTED;
    }

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne()
    @JoinColumn(name = "worker_id")
    private WorkerEntity worker;

}
