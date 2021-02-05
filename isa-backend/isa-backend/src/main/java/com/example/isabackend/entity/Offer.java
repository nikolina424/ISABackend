package com.example.isabackend.entity;

import com.example.isabackend.util.enums.OfferStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "offer")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double price;

    private LocalDate deliveryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    private OfferStatus offerStatus;




}
