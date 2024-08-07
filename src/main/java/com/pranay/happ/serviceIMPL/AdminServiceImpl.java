package com.pranay.happ.serviceIMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pranay.happ.dto.Response;
import com.pranay.happ.entity.Login;
import com.pranay.happ.entity.Role;
import com.pranay.happ.entity.UserRequest;
import com.pranay.happ.repo.LoginRepository;
import com.pranay.happ.repo.RoleRepository;
import com.pranay.happ.repo.UserRepository;
import com.pranay.happ.serviceI.AdminServiceI;

@Service
public class AdminServiceImpl implements AdminServiceI {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Response assignRole(String email, String rolename) {
		Login login = loginRepository.findByEmail(email);

		if (login != null && login.getUserRequest() != null) {
			Role role = roleRepository.findByRolename(rolename);
			if (role != null) {
				UserRequest userRequest = login.getUserRequest();
				userRequest.setRole(role);
				userRepository.save(userRequest);
				return Response.builder().msg("Role Assigned Successfully.").build();
			}
		}
		return Response.builder().msg("Role Assigned Not Successfully.").build();
	}

	@Override
	public List<String> getActiveEmailList() {
		return loginRepository.findAllEmails();
	}

	@Override
	public List<String> getRoleList() {
		return roleRepository.findAllRole();
	}
}