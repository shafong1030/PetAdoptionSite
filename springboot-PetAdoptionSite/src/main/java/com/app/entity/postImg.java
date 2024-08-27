package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Entity
@Document(collection = "postImg")
public class postImg {
	@Id
	private Long postId;
	private Binary image;
	private Long userId;
	public postImg(Long postId, Binary image, Long userId) {
		super();
		this.postId = postId; 
		this.image = image;
		this.userId = userId;
	}
	public postImg() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public Binary getImage() {
		return this.image;
	}
}
