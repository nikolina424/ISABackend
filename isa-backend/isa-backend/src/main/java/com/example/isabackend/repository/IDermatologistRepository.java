package com.example.isabackend.repository;

import com.example.isabackend.entity.Dermatologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDermatologistRepository extends JpaRepository<Dermatologist, Long> {

    Dermatologist findOneById(Long id);
}
