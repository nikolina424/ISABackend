package com.example.isabackend.repository;

import com.example.isabackend.entity.DermatologistExamination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDermatologistExaminationRepository extends JpaRepository<DermatologistExamination, Long> {
    List<DermatologistExamination> findAllByPharmacy_Id(Long id);

    DermatologistExamination findOneById(Long id);
}
