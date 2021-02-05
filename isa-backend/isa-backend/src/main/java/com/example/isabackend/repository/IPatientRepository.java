package com.example.isabackend.repository;

import com.example.isabackend.entity.Patient;
import com.example.isabackend.entity.User;
import com.example.isabackend.util.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {
    Patient findOneById(Long id);

    List<Patient> findAllByDeleted(boolean b);

    List<Patient> findAllByRequestStatus(RequestStatus pending);
}
