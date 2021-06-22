package com.schoolofnet.HelpDesk.services;

import java.util.List;

import com.schoolofnet.HelpDesk.models.Role;

public interface RoleService {
	List<Role> findAll();

	Role create(Role role);

	Boolean delete(Long id);
	
	Role findByName(String name);
	
}
