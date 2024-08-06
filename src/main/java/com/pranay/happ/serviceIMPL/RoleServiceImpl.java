package com.pranay.happ.serviceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pranay.happ.dto.Response;
import com.pranay.happ.entity.Role;
import com.pranay.happ.repo.RoleRepository;
import com.pranay.happ.serviceI.RoleServiceI;

@Service
public class RoleServiceImpl implements RoleServiceI{

	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Response saveRoleData(Role role) {
		Response response = new Response();
        try {
            Role savedRole = roleRepository.save(role);
            
            if (savedRole != null) {
                response.setMsg("Role Data inserted.");
            } else {
                response.setMsg("Role Data not inserted.");
            }
        } catch (Exception e) {
            response.setMsg("Error occurred while inserting role data: " + e.getMessage());
        }
        return response;
    }
}