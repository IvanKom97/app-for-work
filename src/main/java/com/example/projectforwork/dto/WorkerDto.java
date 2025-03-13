package com.example.projectforwork.dto;

import com.example.projectforwork.enums.OptionsWork;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class WorkerDto {
    private String firstName;
    private String secondName;
    @Pattern(regexp = "^\\w+@\\p{L}+\\.\\p{L}{2,10}")
    private String mail;
    private String password;
    private OptionsWork workType;
}
