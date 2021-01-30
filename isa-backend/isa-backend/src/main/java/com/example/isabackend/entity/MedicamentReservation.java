package com.example.isabackend.entity;

import com.example.isabackend.util.enums.MedicamentReservationStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "medicament_reservation")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentReservation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_to_pick")
    private LocalDate dateToPick;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_medicament_id")
    private PharmacyMedicament pharmacyMedicament;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "medicament_reservation_status")
    private MedicamentReservationStatus medicamentReservationStatus;


}
