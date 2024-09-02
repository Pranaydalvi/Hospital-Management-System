package com.pranay.happ.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String diagnosis;
    private String medication;
    private String dosage;
    private String instructions;
    private LocalDateTime prescribedDate;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    @JsonBackReference
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "assigned_doctor_id")
    @JsonBackReference
    private AssignedDoctor assignedDoctor;
    
}