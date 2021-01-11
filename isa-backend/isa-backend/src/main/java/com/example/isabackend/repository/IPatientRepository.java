package com.example.isabackend.repository;

import com.example.isabackend.entity.Patient;
import com.example.isabackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {
    Patient findOneById(Long id);

    Patient findOneByUser(User user);

}