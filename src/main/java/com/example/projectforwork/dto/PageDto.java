package com.example.projectforwork.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageDto {
    private String mail;
    private int page;
    private int requestedCount;
}
