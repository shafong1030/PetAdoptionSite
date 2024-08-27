package com.app.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import com.app.inputMsg;
import com.app.msgDTO;
import com.app.newRegUserDTO;
import com.app.showPostDTO;
import com.app.entity.post;
import com.app.entity.postImg;
import com.app.entity.user;

public interface userService extends UserDetailsService{
	boolean checkEmail(newRegUserDTO nrmDTO);
	user save(newRegUserDTO nrmDTO);
	boolean checkIfValidOldPassword(user user, String oldPassword);
	void changeUserPassword(user user, String newPassword);
	postImg issuePost(String animal, String title, String content, MultipartFile file) throws IOException;
	List<showPostDTO> showPostWithStatusTrue(String keyword);
	List<showPostDTO> showPostsOfUser();
	post findPostById(Long postId); 
	postImg findImgById(Long postId);
	postImg findAllPost();
	showPostDTO createShowPostDTO(Long postId);
	void changePostStatus(Long postId);
	msgDTO sendMessage(inputMsg msg);
	user findUser(Long userId);	
	user findCurrentUser();
	Collection<msgDTO> findAllMessage();
}
