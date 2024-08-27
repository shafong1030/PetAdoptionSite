package com.app.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;

@Data
@Entity
@Table(name = "message")
public class message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long messageId;
	//To whom the message was sent to and receive
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_userId")
    private user sender;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_userId")
    private user receiver;
	private String msgContent;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDateTime msgDateTime;



	public message() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}


	public message(user sender, user receiver, String msgContent, LocalDateTime msgDateTime) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.msgContent = msgContent;
		this.msgDateTime = msgDateTime;
	}
	
	

}
