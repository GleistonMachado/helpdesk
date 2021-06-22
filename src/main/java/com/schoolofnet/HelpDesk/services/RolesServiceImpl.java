package com.schoolofnet.HelpDesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolofnet.HelpDesk.models.Role;
import com.schoolofnet.HelpDesk.repositories.RoleRepository;

@Service
public class RolesServiceImpl implements RoleService {

	@Autowired
	private RoleRepository repository;

	public RolesServiceImpl(RoleRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<Role> findAll() {
		return this.repository.findAll();
	}

	@Override
	public Role create(Role role) {
		role.setName(role.getName().toUpperCase());
		Role roleCreated = repository.save(role);
		return roleCreated;
	}

	@Override
	public Boolean delete(Long id) {
		Optional<Role> role = findById(id);
		
		if(role != null) {
			this.repository.deleteById(role);
			return true;
		} else {
			return false;
		}
	}
	
	private Optional<Role> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Role findByName(String name) {
		return this.repository.findByName(name);
	}



}
