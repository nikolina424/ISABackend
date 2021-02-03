package com.example.isabackend.repository;

import com.example.isabackend.dto.response.PricelistResponse;
import com.example.isabackend.entity.Pricelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPricelistRepository extends JpaRepository<Pricelist, Long> {
    List<Pricelist> getAllByPharmacyId(Long id);

    Pricelist findOneById(Long pricelistId);
}
