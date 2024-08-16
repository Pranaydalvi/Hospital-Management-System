package com.pranay.happ.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pranay.happ.entity.AssignedDoctor;

public interface DoctorRepository extends JpaRepository<AssignedDoctor, Integer>{

	List<AssignedDoctor> findByCatogory(String category);

	AssignedDoctor findByCatogoryAndName(String category, String name);
}
