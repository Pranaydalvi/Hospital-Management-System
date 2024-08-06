package com.pranay.happ.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pranay.happ.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	
	Role findByRolename(String rolename); 
	
}