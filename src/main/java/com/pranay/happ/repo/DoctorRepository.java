package com.pranay.happ.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pranay.happ.entity.AssignedDoctor;

public interface DoctorRepository extends JpaRepository<AssignedDoctor, Integer>{

}
