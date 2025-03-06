package com.example.projectforwork.entity;

import com.example.projectforwork.enums.Roles;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor

public class UserEntity extends AbstractUserEntity {
    public UserEntity(String firstName, String secondName, String mail, String password) {
        super(firstName, secondName, mail, password, Roles.USER);
    }

    @OneToMany(fetch = FetchType.LAZY,
    mappedBy = "user",
    cascade = CascadeType.REMOVE)
    private List<OrderEntity> orders;

}
