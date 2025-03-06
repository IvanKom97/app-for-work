package com.example.projectforwork.entity;
import com.example.projectforwork.enums.Roles;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "workers")
@Entity
@NoArgsConstructor

public class WorkerEntity extends AbstractUserEntity{

    public WorkerEntity(String firstName, String secondName, String mail, String password) {
        super(firstName, secondName, mail, password, Roles.WORKER);
    }

    @OneToMany(mappedBy = "worker",
            cascade = CascadeType.DETACH)
    private List<OrderEntity> ordersForExecute;
}
