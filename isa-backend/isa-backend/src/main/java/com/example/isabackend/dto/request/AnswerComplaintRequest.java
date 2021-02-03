package com.example.isabackend.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerComplaintRequest {
    private Long complaintId;
    private Long patientId;
    private String text;
}
