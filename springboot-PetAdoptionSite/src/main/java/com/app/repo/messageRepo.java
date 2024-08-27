package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.message;

public interface messageRepo extends JpaRepository<message, Long>{
	
}
