package com.example.isabackend.repository;

import com.example.isabackend.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPharmacyRepository extends JpaRepository<Pharmacy, Long> {
    Pharmacy findOneById(Long id);
}
