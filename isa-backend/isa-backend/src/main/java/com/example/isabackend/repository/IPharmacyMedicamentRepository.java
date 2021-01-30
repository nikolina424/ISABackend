package com.example.isabackend.repository;

import com.example.isabackend.entity.PharmacyMedicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPharmacyMedicamentRepository extends JpaRepository<PharmacyMedicament, Long> {
    PharmacyMedicament findOneById(Long id);

    List<PharmacyMedicament> findAllByMedicament_Id(Long id);
}
