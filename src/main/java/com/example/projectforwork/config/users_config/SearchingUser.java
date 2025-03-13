package com.example.projectforwork.config.users_config;

import com.example.projectforwork.entity.AbstractUserEntity;
import com.example.projectforwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
@RequiredArgsConstructor
@Component
public class SearchingUser implements Searching {
    private final UserRepository userRepository;
    @Override
    public Optional<AbstractUserEntity> findUserByMail(String mail) {
        return userRepository.findUserByEmail(mail)
                .map(user -> (AbstractUserEntity) user);
    }
}
