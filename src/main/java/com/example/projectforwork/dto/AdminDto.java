package com.example.projectforwork.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AdminDto {
    private String firstName;
    private String secondName;
    @Pattern(regexp = "^\\w+@\\p{L}+\\.\\p{L}{2,10}$")
    private String mail;
    private String password;
    private String secretCompanyKey;
}
