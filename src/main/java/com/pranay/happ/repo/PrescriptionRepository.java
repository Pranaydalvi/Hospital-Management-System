package com.pranay.happ.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pranay.happ.entity.Prescription;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    
    List<Prescription> findByAssignedDoctorDoctornumber(String doctorNumber);
    
//    @Query(nativeQuery = true, value = "SELECT * FROM prescription WHERE assigned_doctor_id = (SELECT id FROM assigned_doctor WHERE doctornumber = :doctornumber)")
//    List<Prescription> getlistofprescription(@Param("doctornumber")String doctornumber);
    
    Prescription findByAppointment_Id(Long appointmentId);
}