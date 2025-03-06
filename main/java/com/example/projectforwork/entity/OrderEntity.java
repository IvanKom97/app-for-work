package com.example.projectforwork.entity;

import com.example.projectforwork.enums.Priorities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne()
    @JoinColumn(name = "worker_id")
    private WorkerEntity worker;
}
