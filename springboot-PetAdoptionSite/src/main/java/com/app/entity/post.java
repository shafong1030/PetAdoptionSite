package com.app.entity;


import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;

@Entity
@Data
@Table(name = "post")
public class post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate date;
	private String animal;
	private boolean status;
	private String title;
	private String content;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	@JsonBackReference
	private user user;
    
	public post() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public post(LocalDate date, String animal, boolean status, String title, String content, user user) {
		super();
		this.date = date;
		this.animal = animal;
		this.status = status;
		this.title = title;
		this.content = content;
		this.user = user;
	}
	
	public void changeStatus() {
		if(status == true) {
			status =false;
		}else {
			status =true;
		}
	}
    @Override
    public String toString() {
        return "[post id:]" + postId;
    }

}
