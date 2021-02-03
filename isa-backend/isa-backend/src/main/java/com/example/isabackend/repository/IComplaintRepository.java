package com.example.isabackend.repository;

import com.example.isabackend.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComplaintRepository extends JpaRepository<Complaint, Long> {
    Complaint findOneById(Long complaintId);
}
