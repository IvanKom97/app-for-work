package com.example.projectforwork.config.users_config;

import com.example.projectforwork.entity.AbstractUserEntity;
import com.example.projectforwork.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class SearchingAdmin implements Searching {
    private final AdminRepository adminRepository;

    @Override
    public Optional<AbstractUserEntity> findUserByMail(String mail) {
        return adminRepository.findAdminByEmail(mail)
                .map(admin -> (AbstractUserEntity) admin);
    }
}
