package com.app;

import java.time.LocalDate;

import org.bson.types.Binary;

import com.app.entity.user;

import lombok.NoArgsConstructor;

//This DTO is for getting the parameters from html and save to db
@NoArgsConstructor
public class PostDTO {
	private LocalDate date;
	private String animal;
	private Boolean status;//status: true means avaialble, false means adopted
	private String title;
	private String content;
	private user user;
	private Binary image;
	
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
	public Binary getImage() {
		return image;
	}
	public void setImage(Binary image){
		this.image = image;
	}

	public PostDTO(LocalDate date, String animal, Boolean status, String title, String content,
			com.app.entity.user user, Binary image) {
		super();
		this.date = date;
		this.animal = animal;
		this.status = status;
		this.title = title;
		this.content = content;
		this.user = user;
		this.image = image;
	}

}
