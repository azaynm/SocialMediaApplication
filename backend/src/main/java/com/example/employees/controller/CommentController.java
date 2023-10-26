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
import com.example.employees.repository.PostRepository;
import com.example.employees.service.CommentService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	
	@PostMapping("/comments")
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
	    Comment newComment = commentService.createComment(comment);
	    return new ResponseEntity<>(newComment, HttpStatus.CREATED);
	}
	
	@PostMapping("/comments/checkCommentedUser")
	public boolean checkCommentedUser(@RequestParam String username, @RequestParam long commentId) {
	    return commentService.checkCommentedUser(username, commentId);
	}
	
	@GetMapping("/comments")
	public ResponseEntity<List<Comment>> getAllComments(@RequestParam(required = false) Long id) {
	  try {
	    List<Comment> comments = commentService.getAllComments(id);

	    if (comments.isEmpty()) {
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    return new ResponseEntity<>(comments, HttpStatus.OK);
	  } catch (Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	
	@GetMapping("/comment/{postId}")
	public ResponseEntity<List<Comment>> getSpecificPostComments(@PathVariable("postId") long postId) {
	    List<Comment> comments = commentService.getCommentsByPostId(postId);
	    if (comments.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<>(comments, HttpStatus.OK);
	}
	
	@DeleteMapping("/comments/{id}")
	public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
	    commentService.deleteComment(id);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/comments/{id}")
	public ResponseEntity<Comment> updateComment(@PathVariable("id") long id, @RequestBody Comment updatedComment) {
	    return commentService.updateComment(id, updatedComment);
	}

	
	@GetMapping("/{commentId}/{addedUser}")
	public boolean checkRecordExists(@PathVariable long addedUser, @PathVariable long commentId) {
	    return commentService.checkRecordExists(commentId, addedUser);
	}
	
	@DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }
}
