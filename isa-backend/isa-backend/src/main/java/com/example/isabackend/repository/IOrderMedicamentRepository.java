package com.example.isabackend.repository;

import com.example.isabackend.entity.OrderMedicament;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderMedicamentRepository extends JpaRepository<OrderMedicament, Long> {
}
