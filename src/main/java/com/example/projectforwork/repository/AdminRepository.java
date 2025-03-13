package com.example.projectforwork.repository;

import com.example.projectforwork.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, UUID> {

    @Query(value = "from AdminEntity a where a.mail=:mail")
    Optional<AdminEntity> findAdminByEmail(@Param("mail") String mail);
}
