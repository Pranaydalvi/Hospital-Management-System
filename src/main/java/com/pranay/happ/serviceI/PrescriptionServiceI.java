package com.pranay.happ.serviceI;

import java.util.List;

import com.pranay.happ.entity.Prescription;

public interface PrescriptionServiceI {
    
    Prescription createPrescription(Prescription prescription);
    
    List<Prescription> getPrescriptionsByDoctor(String doctorNumber);
    
    Prescription getPrescriptionByAppointment(Long appointmentId);
    
    Prescription updatePrescription(Long prescriptionId, Prescription prescriptionDetails);
    
    void deletePrescription(Long prescriptionId);

	List<Prescription> getAllPrescriptions();
}