package com.app.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.entity.postImg;

public interface postImgRepo extends MongoRepository<postImg, Long>{
	postImg findBypostId(Long postId);
	
}
