package com.example.projectforwork.dto;

import com.example.projectforwork.annotation.ValidWorkTypeForCategory;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class JoinedDtoUserOrder {
    private UserDtoForRegistration userDtoForRegistration;
    @Valid
    private OrderDto orderDto;
}
