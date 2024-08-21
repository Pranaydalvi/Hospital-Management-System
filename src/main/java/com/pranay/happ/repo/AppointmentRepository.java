package com.pranay.happ.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pranay.happ.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	List<Appointment> findByUserRequestUsernumber(String usernumber);

	boolean existsByAppointedDoctorAndDateAndTime(String appointedDoctor, String date, String time);

}
