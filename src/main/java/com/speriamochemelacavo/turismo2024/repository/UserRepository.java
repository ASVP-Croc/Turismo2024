package com.speriamochemelacavo.turismo2024.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.models.users.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String userToFindUserName);
	
	List<User> findByRole(Role userToFindRole);
}


