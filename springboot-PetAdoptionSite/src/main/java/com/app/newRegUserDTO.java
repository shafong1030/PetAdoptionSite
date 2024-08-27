package com.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import com.app.entity.post;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class newRegUserDTO {
	private String password;
	private String lastName;
	private String firstName;
	private String email;
	private LocalDate regDate;
	private Integer rate;
	private Collection<post> posts;
	
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDate refDate) {
		this.regDate = refDate;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = null;
	}
	public Collection<post> getPosts() {
		return posts;
	}
	public void setPosts(ArrayList<post> posts) {
		this.posts = posts;
	}


	

}
