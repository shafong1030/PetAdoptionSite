package com.app.entity;

import java.time.LocalDate;
import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;


@Data
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class user {
		

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
		private String lastName;
	private String firstName;
	private String email;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate regDate;
	private Integer rate;
	private String password;
		
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy ="user")
	@JsonManagedReference
	private Collection<post> posts;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(
					name = "user_userId", referencedColumnName = "userId"),
			inverseJoinColumns = @JoinColumn(
					name = "role_userId", referencedColumnName = "userId"))
	private Collection<role> roles;


	public user() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public user(String lastName, String firstName, String email, LocalDate regDate, Integer rate, String password,
			Collection<post> posts, Collection<role> roles) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.regDate = regDate;
		this.rate = rate;
		this.password = password;
		this.posts = posts;
		this.roles = roles;
	}

		public void addPost(post post) {
		posts.add(post);		
	}


	    @Override
	    public String toString() {
	        return "[user id:]" + userId;
	    }


	
}
