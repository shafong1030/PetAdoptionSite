package com.app.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.app.inputMsg;
import com.app.msgDTO;
import com.app.service.userService;

@Controller
public class messageController{
	@Autowired
	private userService ms;
	
	
	public messageController(userService ms) {
		super();
		this.ms = ms;
	}
	
	//After the user click on the button of the post they are interested, they can send message
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
	public Collection<msgDTO> sendMessage(@Payload inputMsg msg) throws Exception{
    	    System.out.println("Get started");
    	    System.out.println("Print message" + msg.getText() + msg.getReceiverId());
    	    ms.sendMessage(msg);
		    return ms.findAllMessage();
		    }
    

}
