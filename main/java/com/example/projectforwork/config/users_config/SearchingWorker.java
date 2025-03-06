package com.example.projectforwork.config.users_config;

import com.example.projectforwork.entity.AbstractUserEntity;
import com.example.projectforwork.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class SearchingWorker implements Searching{

    private final WorkerRepository workerRepository;
    @Override
    public Optional<AbstractUserEntity> findUserByMail(String mail) {
        return workerRepository.findWorkerByEmail(mail)
                .map(worker -> (AbstractUserEntity) worker);
    }
}
