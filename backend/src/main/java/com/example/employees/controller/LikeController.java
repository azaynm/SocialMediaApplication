package com.example.employees.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.employees.model.Comment;
import com.example.employees.model.Like;
import com.example.employees.model.Post;
import com.example.employees.repository.CommentRepository;
import com.example.employees.repository.LikeRepository;
import com.example.employees.repository.PostRepository;
import com.example.employees.service.LikeService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class LikeController {
	
	@Autowired
	LikeService likeService;
	
	
	@PostMapping("/likes")
	  public ResponseEntity<Like> createLike(@RequestBody Like like) {
	    return likeService.createLike(like);
	  }
	
	@GetMapping("/like/count/{postId}")
	  public ResponseEntity<Long> getNumOfLikes(@PathVariable("postId") long postId) {
	    return likeService.getNumOfPostLikes(postId);
	  }
	
	@GetMapping("/likes")
	  public ResponseEntity<List<Like>> getAllLikes(@RequestParam(required = false) Long id) {
	    return likeService.getAllLikes(id);
	  }
	
	@GetMapping("/like/{likeId}")
	  public ResponseEntity<List<Like>> getSpecificPostLikes(@PathVariable("likeId") long likeId) {
	    return likeService.getSpecificPostLikes(likeId);
	  }
	
	@DeleteMapping("/likes/{id}")
	  public ResponseEntity<HttpStatus> deleteLike(@PathVariable("id") long id) {
	    return likeService.deleteLike(id);
	  }
	
	@PostMapping("/likes/check")
	  public Long checkLike(@RequestBody Like like) {
	    return likeService.checkLike(like);
	  
	}
}

