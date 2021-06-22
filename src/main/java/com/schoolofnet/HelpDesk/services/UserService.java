package com.schoolofnet.HelpDesk.services;

import java.util.List;

import com.schoolofnet.HelpDesk.models.User;

public interface UserService {
	List<User> findAll();
	
	User create(User user);
	
	Boolean update(Long id, User user);
	
	Boolean delete(Long id);

	User show(Long id);

	List<User> findAllWhereRoleEquals(Long role_id, Long user_id);
}
