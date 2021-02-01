package com.example.isabackend.repository;

import com.example.isabackend.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IShiftRepository extends JpaRepository<Shift, Long> {
    List<Shift> findAllByPharmacy_Id(Long pharmacyId);

    List<Shift> findAllByDermatologist_Id(Long itemId);

}
