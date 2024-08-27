package com.app;

import java.time.LocalDate;

import com.app.entity.user;

import lombok.NoArgsConstructor;

//This DTO is for getting the post data from DB and show on html 
@NoArgsConstructor
public class showPostDTO {
	private Long postId;
	private LocalDate date;
	private String animal;
	private Boolean status;//status: true means avaialble, false means adopted
	private String title;
	private String content;
	private user user;
	private String image;
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getAnimal() {
		return animal;
	}
	public void setAnimal(String animal) {
		this.animal = animal;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public user getUser() {
		return user;
	}
	public void setUser(user user) {
		this.user = user;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image){
		this.image = image;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public showPostDTO(LocalDate date, String animal, Boolean status, String title, String content,
			com.app.entity.user user, String image, Long postId) {
		super();
		this.date = date;
		this.animal = animal;
		this.status = status;
		this.title = title;
		this.content = content;
		this.user = user;
		this.image = image;
		this.postId = postId;
	}

}
