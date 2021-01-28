package com.example.isabackend.repository;

import com.example.isabackend.entity.PharmacyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPharmacyAdminRepository extends JpaRepository<PharmacyAdmin, Long> {
    PharmacyAdmin findOneById(Long id);

    PharmacyAdmin findOneByUser_id(Long id);
}
