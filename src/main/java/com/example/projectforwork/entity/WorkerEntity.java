package com.example.projectforwork.entity;
import com.example.projectforwork.enums.OptionsWork;
import com.example.projectforwork.enums.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "workers")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class WorkerEntity extends AbstractUserEntity{

    @Column(name = "work_type")
    @Enumerated(value = EnumType.STRING)
    private OptionsWork optionsWork;

    public WorkerEntity(String firstName,
                        String secondName,
                        String mail,
                        String password,
                        OptionsWork optionsWork) {
        super(firstName, secondName, mail, password, Roles.WORKER);
        this.optionsWork = optionsWork;
    }

    @OneToMany(mappedBy = "worker",
            cascade = CascadeType.DETACH)
    private List<OrderEntity> ordersForExecute;
}
