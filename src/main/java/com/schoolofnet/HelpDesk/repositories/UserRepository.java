package com.schoolofnet.HelpDesk.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.schoolofnet.HelpDesk.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findById(Long id);

	@Query(value = "select u.* from users u inner join users_roles ur on u.id = ur.user_id where ur.role_id = :role_id and u.id not in(:user_id)", 
			nativeQuery = true)
	public List<User> findAllWhereRoleEquals(@Param("role_id") Long role_id, @Param("user_id") Long user_id);

	User findByEmail(String email);
}
