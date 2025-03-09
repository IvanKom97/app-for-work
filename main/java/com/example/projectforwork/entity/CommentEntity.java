package com.example.projectforwork.entity;

import com.example.projectforwork.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "comments")

public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "post_date")
    private LocalDate postDate;
    private String comment;
    @Column(name = "commentator_name")
    private String commentatorName;
    @Column(name = "commentator_role")
    @Enumerated(value = EnumType.STRING)
    private Roles commentatorRole;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
    @Builder
    public CommentEntity(String comment,
                          String commentatorName,
                          Roles commentatorRole,
                          OrderEntity order) {
        this.postDate = LocalDate.now();
        this.comment = comment;
        this.commentatorName = commentatorName;
        this.commentatorRole = commentatorRole;
        this.order = order;
    }
}
