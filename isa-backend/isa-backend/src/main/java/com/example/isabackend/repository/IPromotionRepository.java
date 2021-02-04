package com.example.isabackend.repository;

import com.example.isabackend.dto.response.PromotionResponse;
import com.example.isabackend.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPromotionRepository extends JpaRepository<Promotion, Long> {
}
