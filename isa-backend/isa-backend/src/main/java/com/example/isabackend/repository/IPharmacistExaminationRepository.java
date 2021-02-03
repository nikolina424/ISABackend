package com.example.isabackend.repository;

import com.example.isabackend.entity.PharmacistExamination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPharmacistExaminationRepository extends JpaRepository<PharmacistExamination, Long> {
    List<PharmacistExamination> findAllByPatient_Id(Long id);

    PharmacistExamination findOneById(Long id);
}
