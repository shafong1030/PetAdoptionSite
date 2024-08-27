package com.app;

import com.app.entity.user;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class msgDTO {
	private user sender;
	private user receiver;
	private String msgContent;
	private String msgDateTime;
	
	public msgDTO(com.app.entity.user sender, com.app.entity.user receiver, String msgContent, String msgDateTime) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.msgContent = msgContent;
		this.msgDateTime = msgDateTime;
	}

    

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMsgDateTime() {
		return msgDateTime;
	}

	public void setMsgDateTime(String msgDateTime) {
		this.msgDateTime = msgDateTime;
	}



	public user getSender() {
		return sender;
	}


	public void setSender(user sender) {
		this.sender = sender;
	}


	public user getReceiver() {
		return receiver;
	}


	public void setReceiver(user receiver) {
		this.receiver = receiver;
	}

}
