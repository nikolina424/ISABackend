package com.example.isabackend.repository;

import com.example.isabackend.entity.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPharmacistRepository extends JpaRepository<Pharmacist, Long> {
}
