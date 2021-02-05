package com.example.isabackend.repository;

import com.example.isabackend.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOfferRepository extends JpaRepository<Offer, Long> {
    Offer findOneById(Long offerId);
}
