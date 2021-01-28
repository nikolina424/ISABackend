package com.example.isabackend.repository;

import com.example.isabackend.entity.PharmacyMedicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPharmacyMedicamentRepository extends JpaRepository<PharmacyMedicament, Long> {
    PharmacyMedicament findOneById(Long id);
}
