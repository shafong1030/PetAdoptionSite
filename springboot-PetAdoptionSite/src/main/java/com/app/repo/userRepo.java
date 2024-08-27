package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.user;

@Repository

public interface userRepo extends JpaRepository<user,Long>{
	//check if the email provided by the user in an new application exist
	boolean existsByEmail(String email);
	//Use for looking up the user email & password when login
	user findByEmail(String email);
	user findByUserId(Long userId);
}
