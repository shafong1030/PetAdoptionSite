package com.app.service;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.PostDTO;
import com.app.inputMsg;
import com.app.msgDTO;
import com.app.newRegUserDTO;
import com.app.showPostDTO;
import com.app.entity.message;
import com.app.entity.post;
import com.app.entity.postImg;
import com.app.entity.role;
import com.app.entity.user;
import com.app.repo.messageRepo;
import com.app.repo.myUser;
import com.app.repo.postImgRepo;
import com.app.repo.postRepo;
import com.app.repo.userRepo;

@Service
public class userServiceImp implements userService{
	private userRepo repo;
	private postRepo postrepo;
	private postImgRepo postimgrepo;
	private messageRepo msgrepo;
	
	@Lazy
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
    private SimpMessagingTemplate template;
	
	public userServiceImp(userRepo repo, postRepo postrepo, postImgRepo postimgrepo,messageRepo msgrepo) {
		super();
		this.repo = repo;
		this.postrepo = postrepo;
		this.postimgrepo = postimgrepo;
		this.msgrepo = msgrepo;
	}
	
	@Override
	public boolean checkEmail(newRegUserDTO nrmDTO) {
		String email = nrmDTO.getEmail();
		boolean result = repo.existsByEmail(email);					
		return result;
		
	}
		
	@Override
	public user save(newRegUserDTO nruDTO) {
		LocalDate dateOfReg = LocalDate.now();
		user user= new user(nruDTO.getLastName(), nruDTO.getFirstName(),nruDTO.getEmail(), dateOfReg, nruDTO.getRate(), passwordEncoder.encode(nruDTO.getPassword()), 
				new ArrayList<post>(), Arrays.asList(new role("ROLE_USER")));
		return repo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		user user = repo.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password."); 
		}
		return new myUser(user);
		
	}
	@Override
	public boolean checkIfValidOldPassword(user user, String oldPassword) {
		if(BCrypt.checkpw(oldPassword, user.getPassword())) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public void changeUserPassword(user user, String newPassword) {
		user.setPassword(passwordEncoder.encode(newPassword));
		repo.save(user);
	}
	
	@Override
	public user findCurrentUser() {
		return repo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
	}
    
	@Override
	public postImg issuePost(String animal,String title, String content,MultipartFile file) throws IOException{
		//define today
		LocalDate dateOfPost = LocalDate.now();
		//Change the received file to binary for storage
		Binary toBin = new Binary(BsonBinarySubType.BINARY, file.getBytes());
		//Get the current user from authentication
		user user = repo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		//Create a npDtO for storing the received value
		PostDTO npDTO = new PostDTO(dateOfPost, animal, true, title, content,user, toBin);
		//save the post in repo and return the post
		post post = postrepo.save(new post(npDTO.getDate(),npDTO.getAnimal(), npDTO.getStatus(), npDTO.getTitle(), npDTO.getContent(), npDTO.getUser()));
		//Add the post to user field member "posts"
		user.addPost(post);
		repo.save(user);
		//finally, save the post image in postImg DB
		return postimgrepo.save(new postImg(post.getPostId(), npDTO.getImage(),user.getUserId()));
	}
	
	//Show the posts according to the serach result from user
	@Override
	public List<showPostDTO> showPostWithStatusTrue(String keyword) {
		//First, get the lists from repository
		List<post> postList = postrepo.findAll();
		List<postImg> postImgList = postimgrepo.findAll();
		//Second, check the length of post and postImg should be the same,if the size not the same, return null
		if(postList.size() != postImgList.size()) {
			return null;
		}else {
			//Third, check if the list from repository is null
			if(postList == null) {
				return null;
			}else {
			//Forth, start do the filtering
				//Create a list as a container to wrap all the necessary elements needed
				List<showPostDTO> showPostDTOList = new ArrayList<>();
				//Loop the list from latest to oldest
				for(int i = postList .size()-1 ; i>=0 ;i--) {
					//If the status is false(already adopted), skip this loop
					if(postList.get(i).isStatus()==false) {
						continue;
					}
					//If the animal var does not contain the keywords from user,skip this loop
					if(keyword != "" & keyword != null) {
						String[] tokens = keyword.split(",");
						int counter = 0;
						for(String token : tokens) {
							System.out.println(token);
							if(postList.get(i).getAnimal().contains(token)) {
								counter += 1;
							}
						}
						if(counter ==0) {
							continue;
						}
					}
					//If the status = true(not yet adopted)  & animal matches search keyword, add the element in List 
					showPostDTO spDTO = new showPostDTO(postList.get(i).getDate(),postList.get(i).getAnimal(),postList.get(i).isStatus(),
							postList.get(i).getTitle(),postList.get(i).getContent(),postList.get(i).getUser(),
							Base64.getEncoder().encodeToString(postImgList.get(i).getImage().getData()),postList.get(i).getPostId());
				
				showPostDTOList.add(spDTO);
				
				}
				showPostDTOList.forEach(i ->System.out.println(i.getAnimal()));
				return showPostDTOList;
			}
			
		}
	}
	
	
	

	@Override
	public post findPostById(Long postId){
		post post =  postrepo.findBypostId(postId);
		return post;//default JPA method
	}
	
	@Override
	public postImg findAllPost() {
		postImg postimg = postimgrepo.findAll().get(0);
		System.out.println(postimg.getImage());
			return postimg;
		}

	@Override
	public postImg findImgById(Long postId) {
		postImg postimg = postimgrepo.findBypostId(postId);
		return postimg;
	}
	
	@Override
	public user findUser(Long userId) {
		return(repo.findByUserId(userId));
	}

	@Override
	public showPostDTO createShowPostDTO(Long postId) {
		post post =  postrepo.findBypostId(postId);
		postImg postimg = postimgrepo.findBypostId(postId);
		if(post == null || postimg ==null) {
			return null;
		}else {
			showPostDTO spDTO = new showPostDTO(post.getDate(), post.getAnimal(), post.isStatus(), post.getTitle(), post.getContent(),
					post.getUser(),Base64.getEncoder().encodeToString(postimg.getImage().getData()), post.getPostId());
			return spDTO;
		}
		
	}

	@Override
	public List<showPostDTO> showPostsOfUser() {
		//Get all the data from post and postImg DB
		List<post> postList = postrepo.findAll();
		List<postImg> postImgList = postimgrepo.findAll();
		//Check the length of two lists
		if(postList.size() != postImgList.size()) {
			return null;
		}else {
			//After confirming the lists are ok, retrieve the posts issued by the user
			user user = repo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
			List<showPostDTO> posts = new ArrayList<>();
			for(int i = postList.size()-1 ; i>=0;i--) {
				if(postList.get(i).getUser().equals(user)) {
					posts.add(new showPostDTO(postList.get(i).getDate(),postList.get(i).getAnimal(),postList.get(i).isStatus(),
							postList.get(i).getTitle(),postList.get(i).getContent(),postList.get(i).getUser(),
							Base64.getEncoder().encodeToString(postImgList.get(i).getImage().getData()),postList.get(i).getPostId()));
				}else {
					continue;
				}
			}
			return posts;
		}

	}

	@Override
	public void changePostStatus(Long postId) {
		post post = postrepo.findBypostId(postId);
		post.changeStatus();
		postrepo.save(post);
			
	}
	


	@Override
	public msgDTO sendMessage(inputMsg msg) {
		System.out.print("get sender");
		user sender = repo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.print("get Receiver");
		user receiver = findUser(msg.getReceiverId());
		LocalDateTime msgDateTime = LocalDateTime.now();
		System.out.print("save msg");
		message savedMsg = msgrepo.save(new message(sender, receiver, msg.getText(),msgDateTime));
		System.out.print("finish saving msg");
		return new msgDTO(savedMsg.getSender(), savedMsg.getReceiver(), savedMsg.getMsgContent(),savedMsg.getMsgDateTime().toString());
	}
	
	@Override
	public Collection<msgDTO> findAllMessage(){
		Collection<message> messageList = msgrepo.findAll();
		Collection<msgDTO> msgDTOList = new ArrayList<>();
		for(message msg : messageList) {
			msgDTOList.add(new msgDTO(msg.getSender(),msg.getReceiver(),msg.getMsgContent(),msg.getMsgDateTime().toString()));
		}
		return msgDTOList;
	}
		
	
}
