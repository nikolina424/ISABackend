package com.example.isabackend.repository;

import com.example.isabackend.entity.PharmacyMedicament;
import com.example.isabackend.entity.PriceMedicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPriceMedicamentRepository extends JpaRepository<PriceMedicament, Long> {

}
