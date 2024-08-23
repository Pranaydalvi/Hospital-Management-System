package com.pranay.happ.serviceI;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pranay.happ.entity.Login;
import com.pranay.happ.repo.LoginRepository;

@Service
public class UserService implements UserDetailsService{

	
	@Autowired
	private LoginRepository loginRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Login login =loginRepository.findByEmail(email);
		return new User(login.getEmail(), login.getPassword(), new ArrayList<>());
	}

}