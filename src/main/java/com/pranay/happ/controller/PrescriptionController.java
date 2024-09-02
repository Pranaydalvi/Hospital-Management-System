package com.pranay.happ.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pranay.happ.entity.Prescription;
import com.pranay.happ.serviceI.PrescriptionServiceI;

@RestController
@Validated
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionServiceI prescriptionService;

    @PostMapping("/add")
    public ResponseEntity<String> addPrescription(@RequestBody Prescription prescription) {
        Prescription newPrescription = prescriptionService.createPrescription(prescription);
        if(newPrescription !=null) {
        	return new ResponseEntity<String>("Prescription added!", HttpStatus.CREATED);
        }else {
        	return new ResponseEntity<String>("Prescription not added!", HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping("/doctor/{doctorNumber}")
    public ResponseEntity<List<Prescription>> getPrescriptionsByDoctor(@PathVariable String doctorNumber) {
        List<Prescription> prescriptions = prescriptionService.getPrescriptionsByDoctor(doctorNumber);
        return new ResponseEntity<List<Prescription>>(prescriptions,HttpStatus.OK);
    }

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<Prescription> getPrescriptionByAppointment(@PathVariable Long appointmentId) {
        Prescription prescription = prescriptionService.getPrescriptionByAppointment(appointmentId);
        return new ResponseEntity<Prescription>(prescription,HttpStatus.OK);
    }
    
    @PutMapping("/{prescriptionId}")
    public ResponseEntity<Prescription> updatePrescription(
            @PathVariable Long prescriptionId,
            @RequestBody Prescription prescriptionDetails) {
        Prescription updatedPrescription = prescriptionService.updatePrescription(prescriptionId, prescriptionDetails);
        return new ResponseEntity<Prescription>(updatedPrescription,HttpStatus.OK);
    }

    @DeleteMapping("/{prescriptionId}")
    public ResponseEntity<String> deletePrescription(@PathVariable Long prescriptionId) {
        prescriptionService.deletePrescription(prescriptionId);
        return new ResponseEntity<String>("Prescription with Prescription id = "+ prescriptionId+" deleted",HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
        return new ResponseEntity<List<Prescription>>(prescriptions,HttpStatus.OK);
    }
}