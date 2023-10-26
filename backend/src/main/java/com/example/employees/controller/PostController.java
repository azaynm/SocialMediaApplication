package com.example.employees.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.employees.model.Like;
import com.example.employees.model.Post;
import com.example.employees.repository.PostRepository;
import com.example.employees.service.LikeService;
import com.example.employees.service.PostService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	PostRepository postRepository;	
	
	@Autowired
	LikeService likeService;
	
	@Autowired
	PostService postService;

	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getAllPosts(@RequestParam(required = false) String title) {
	    List<Post> posts = postService.getAllPosts(title);
	    if (posts.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/posts/{id}")
	public ResponseEntity<HttpStatus> deletePost(@PathVariable("id") Integer id) {
	    postService.deletePostById(id);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	     
	
	@RequestMapping(value = "/posts", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Post> createPost(
	        @RequestParam(value = "userImage", required = false) String userImage,
	        @RequestParam(value="userId", required=true) final String userId,
	        @RequestParam(value = "postImage", required = false) MultipartFile postImage,
	        @RequestParam(value="title", required=true) final String title)
	        {

	    try {
	        Post _post = postService.createPost(userImage, userId, postImage, title);
	        return new ResponseEntity<>(_post, HttpStatus.CREATED);
	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	

	
//	@PutMapping("/posts/{id}")
//	  public ResponseEntity<Post> updatePost(@PathVariable("id") long id, @RequestBody Post post) {
//	    Optional<Post> postData = postRepository.findById(id);
//
//	    if (postData.isPresent()) {
//	      Post _post = postData.get();
//	      _post.setTitle(post.getTitle());
//	      _post.setAdded_user(post.getAdded_user());
//	      _post.setTime(post.getTime());
//	      return new ResponseEntity<>(postRepository.save(_post), HttpStatus.OK);
//	    } else {
//	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//	  }
	
	
}
