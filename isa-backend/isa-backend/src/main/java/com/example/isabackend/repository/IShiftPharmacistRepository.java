package com.example.isabackend.repository;

import com.example.isabackend.entity.ShiftPharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShiftPharmacistRepository extends JpaRepository<ShiftPharmacist, Long> {
}
