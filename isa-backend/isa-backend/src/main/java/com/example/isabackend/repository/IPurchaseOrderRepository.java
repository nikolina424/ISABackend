package com.example.isabackend.repository;

import com.example.isabackend.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    PurchaseOrder findOneById(Long purchaseOrderId);
}
