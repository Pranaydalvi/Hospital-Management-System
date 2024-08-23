package com.pranay.happ.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pranay.happ.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	List<Appointment> findByUserRequestUsernumber(String usernumber);

	boolean existsByAppointedDoctorAndDateAndTime(String appointedDoctor, String date, String time);
	
	@Query(value = "SELECT COUNT(*) FROM appointment WHERE doctornumber = :doctornumber AND date = :date AND time = :time", nativeQuery = true)
    long isDoctorBooked(@Param("doctornumber") String doctornumber, 
                           @Param("date") String date, 
                           @Param("time") String time);

}
