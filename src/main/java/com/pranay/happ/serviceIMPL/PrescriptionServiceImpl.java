package com.pranay.happ.serviceIMPL;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pranay.happ.entity.Prescription;
import com.pranay.happ.exception.ResourceNotFoundException;
import com.pranay.happ.repo.PrescriptionRepository;
import com.pranay.happ.serviceI.PrescriptionServiceI;

@Service
public class PrescriptionServiceImpl implements PrescriptionServiceI {

    @Autowired
	private PrescriptionRepository prescriptionRepository;

    @Override
    public Prescription createPrescription(Prescription prescription) {
        prescription.setPrescribedDate(LocalDateTime.now());
        return prescriptionRepository.save(prescription);
    }

    @Override
    public List<Prescription> getPrescriptionsByDoctor(String doctorNumber) {
        return prescriptionRepository.findByAssignedDoctorDoctornumber(doctorNumber);
    }

    @Override
    public Prescription getPrescriptionByAppointment(Long appointmentId) {
        return prescriptionRepository.findByAppointment_Id(appointmentId);
    }

    @Override
    public Prescription updatePrescription(Long prescriptionId, Prescription prescriptionDetails) {
        Optional<Prescription> optionalPrescription = prescriptionRepository.findById(prescriptionId);
        if (optionalPrescription.isPresent()) {
            Prescription existingPrescription = optionalPrescription.get();
            existingPrescription.setDiagnosis(prescriptionDetails.getDiagnosis());
            existingPrescription.setMedication(prescriptionDetails.getMedication());
            existingPrescription.setDosage(prescriptionDetails.getDosage());
            existingPrescription.setInstructions(prescriptionDetails.getInstructions());
            return prescriptionRepository.save(existingPrescription);
        } else {
            throw new ResourceNotFoundException("Prescription not found with id: " + prescriptionId);
        }
    }

    @Override
    public void deletePrescription(Long prescriptionId) {
        if (prescriptionRepository.existsById(prescriptionId)) {
            prescriptionRepository.deleteById(prescriptionId);
        } else {
            throw new ResourceNotFoundException("Prescription not found with id: " + prescriptionId);
        }
    }

	@Override
	public List<Prescription> getAllPrescriptions() {
		return prescriptionRepository.findAll();
	}
}