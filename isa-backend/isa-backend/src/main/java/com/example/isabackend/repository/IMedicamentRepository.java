package com.example.isabackend.repository;


import com.example.isabackend.entity.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IMedicamentRepository  extends JpaRepository<Medicament, Long> {
    Medicament findOneById(Long id);
}
