package com.example.isabackend.entity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "supplier_medicament")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierMedicament {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicament_id")
    private Medicament medicament;
}
