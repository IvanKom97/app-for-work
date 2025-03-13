package com.example.projectforwork.entity;

import com.example.projectforwork.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.regex.Pattern;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "post_date")
    private String postDate;
    private String comment;
    @Column(name = "commentator_mail")
    private String commentatorMail;
    @Column(name = "commentator_role")
    @Enumerated(value = EnumType.STRING)
    private Roles commentatorRole;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @Builder
    public CommentEntity(String comment,
                          String commentatorMail,
                          Roles commentatorRole,
                          OrderEntity order) {
        this.postDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        this.comment = comment;
        this.commentatorMail = commentatorMail;
        this.commentatorRole = commentatorRole;
        this.order = order;
    }
}
