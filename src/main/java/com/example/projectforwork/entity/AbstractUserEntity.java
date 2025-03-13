package com.example.projectforwork.entity;
import com.example.projectforwork.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class AbstractUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;
    @Pattern(regexp = "^\\w+@\\p{L}+\\.\\p{L}{2,10}$")
    private String mail;
    @NotBlank
    private String password;
    @Column(name = "registration_date")
    private LocalDate registrationDate;
    @Enumerated(value = EnumType.STRING)
    private Roles role;
    private byte[] photo;
    @Builder
    public AbstractUserEntity(String firstName, String secondName, String mail, String password, Roles role) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.mail = mail;
        this.password = password;
        this.registrationDate = LocalDate.now();
        this.role = role;
    }
}
