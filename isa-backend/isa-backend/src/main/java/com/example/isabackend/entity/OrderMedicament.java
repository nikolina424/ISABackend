package com.example.isabackend.entity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "order_medicament")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMedicament {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicament_id")
    private Medicament medicament;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;
}
