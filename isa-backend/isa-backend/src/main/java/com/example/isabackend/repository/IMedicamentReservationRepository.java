package com.example.isabackend.repository;

import com.example.isabackend.entity.Medicament;
import com.example.isabackend.entity.MedicamentReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMedicamentReservationRepository extends JpaRepository<MedicamentReservation, Long> {
    List<MedicamentReservation> findAllByPatient_Id(Long id);

    MedicamentReservation findOneById(Long id);
}
