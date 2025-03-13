package com.example.projectforwork.dto;

import com.example.projectforwork.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CommentDto {
    private String comment;
    private String datePublication;
    private String mail;
    private Roles role;
}
