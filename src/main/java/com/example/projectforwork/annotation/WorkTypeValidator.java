package com.example.projectforwork.annotation;

import com.example.projectforwork.dto.OrderDto;
import com.example.projectforwork.exceptions.DifferentTypesWorkException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class WorkTypeValidator implements ConstraintValidator<ValidWorkTypeForCategory, OrderDto> {
    @Override
    public boolean isValid(OrderDto orderDto, ConstraintValidatorContext constraintValidatorContext) {
        if (orderDto.getTypesRepairs() == null || orderDto.getOption() == null) {
            return false;
        }
        if (!orderDto.getTypesRepairs().equals(orderDto.getOption().getTypesRepairs())) {
            throw new DifferentTypesWorkException(orderDto.getTypesRepairs());
        }
        return true;
    }
}
