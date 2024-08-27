package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.post;

@Repository
public interface postRepo extends JpaRepository<post,Long>{
	post findBypostId(Long postId);
	List<post> findAll();
}
