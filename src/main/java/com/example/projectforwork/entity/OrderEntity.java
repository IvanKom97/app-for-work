package com.example.projectforwork.entity;

import com.example.projectforwork.dto.OrderDto;
import com.example.projectforwork.enums.OptionsWork;
import com.example.projectforwork.enums.OrderStatus;
import com.example.projectforwork.enums.Priorities;
import com.example.projectforwork.enums.TypesRepairs;
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
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type_repairs")
    private TypesRepairs typesRepairs;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "option_work")
    private OptionsWork optionsWork;
    @Enumerated(value = EnumType.STRING)
    private Priorities priorities;
    private LocalDate publicationDate;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;
    @Column(name = "in_process_since_date")
    private LocalDate inProcessSinceDate;
    private int price;

    @Builder
    public OrderEntity(OrderDto orderDto, UserEntity user) {
        this.typesRepairs = orderDto.getTypesRepairs();
        this.optionsWork = orderDto.getOption();
        this.user = user;
        this.priorities = Priorities.MIDDLE;
        this.publicationDate = LocalDate.now();
        this.orderStatus = OrderStatus.NOT_STARTED;
        this.price = orderDto.getPrice();
    }

    @Builder
    public OrderEntity(OrderDto orderDto) {
        this.typesRepairs = orderDto.getTypesRepairs();
        this.optionsWork = orderDto.getOption();
        this.priorities = Priorities.MIDDLE;
        this.publicationDate = LocalDate.now();
        this.orderStatus = OrderStatus.NOT_STARTED;
        this.price = orderDto.getPrice();
    }

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne()
    @JoinColumn(name = "worker_id")
    private WorkerEntity worker;

}
