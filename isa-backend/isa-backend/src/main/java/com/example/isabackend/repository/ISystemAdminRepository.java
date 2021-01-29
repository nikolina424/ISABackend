package com.example.isabackend.repository;

import com.example.isabackend.entity.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISystemAdminRepository extends JpaRepository<SystemAdmin, Long> {
}
