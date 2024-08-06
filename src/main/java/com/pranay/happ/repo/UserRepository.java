package com.pranay.happ.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pranay.happ.entity.UserRequest;

@Repository
public interface UserRepository extends JpaRepository<UserRequest, Integer>{

}
