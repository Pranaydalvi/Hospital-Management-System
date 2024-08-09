package com.pranay.happ.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pranay.happ.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

}
