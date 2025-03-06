package com.example.projectforwork.repository;

import com.example.projectforwork.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query(value = "from UserEntity u where u.mail=:mail")
    Optional<UserEntity> findUserByEmail(@Param("mail") String mail);
}
