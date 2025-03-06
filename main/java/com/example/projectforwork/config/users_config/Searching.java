package com.example.projectforwork.config.users_config;


import com.example.projectforwork.entity.AbstractUserEntity;

import java.util.Optional;

public interface Searching {

    Optional<AbstractUserEntity> findUserByMail(String mail);
}
