package com.example.projectforwork.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserDtoForAdmin {
    private String firstName;
    private String secondName;
    private String mail;
    private List<OrderDtoForAdmin> orders;
}
