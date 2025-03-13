package com.example.projectforwork.entity;

import com.example.projectforwork.enums.Roles;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "admins")
@NoArgsConstructor

public class AdminEntity extends AbstractUserEntity{


    public AdminEntity(String firstName, String secondName, String mail, String password) {
        super(firstName, secondName, mail, password, Roles.ADMIN);
    }
}
