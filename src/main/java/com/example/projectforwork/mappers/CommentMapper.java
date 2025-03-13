package com.example.projectforwork.mappers;

import com.example.projectforwork.dto.CommentDto;
import com.example.projectforwork.entity.AbstractUserEntity;
import com.example.projectforwork.entity.CommentEntity;
import com.example.projectforwork.entity.OrderEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentMapper  {

    static <T extends AbstractUserEntity> CommentEntity createCommentEntity(T t, OrderEntity order, String comment) {
        return CommentEntity.builder()
                .commentatorMail(t.getMail())
                .commentatorRole(t.getRole())
                .comment(comment)
                .order(order)
                .build();
    }

    static CommentDto fromEntityToDto(CommentEntity comment) {
        return CommentDto.builder()
                .role(comment.getCommentatorRole())
                .mail(comment.getCommentatorMail())
                .datePublication(comment.getPostDate())
                .comment(comment.getComment())
                .build();
    }
    static List<CommentDto> fromCommentEntityPageToCommentDtoList(Page<CommentEntity> page) {
        return page.map(CommentMapper::fromEntityToDto)
                .stream().toList();
    }
}
