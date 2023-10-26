package com.example.employees.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.employees.model.Comment;
import com.example.employees.model.Like;
import com.example.employees.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	
	public Comment createComment(Comment comment) {
            return commentRepository.save(new Comment(
                    comment.getPostId(),
                    comment.getUsername(),
                    comment.getUserImage(),
                    comment.getAdded_user(),
                    comment.getComment(),
                    comment.getTime()
            ));
    }
	
	public boolean checkCommentedUser(String username, long commentId) {
	    List<Like> userComments = commentRepository.checkCommentedUser(username, commentId);
	    return !userComments.isEmpty();
	}
	
	public List<Comment> getAllComments(Long id) {
	    List<Comment> comments = new ArrayList<Comment>();
	    if (id == null)
	        commentRepository.findAll().forEach(comments::add);
	    else
	        commentRepository.findByIdContaining(id).forEach(comments::add);

	    return comments;
	  }
	
	public List<Comment> getCommentsByPostId(long postId) {
        return commentRepository.findByPostId(postId);
    }
	
	public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }
	
	public boolean checkRecordExists(long commentId, long addedUser) {
        List<Comment> record = commentRepository.findByCommentIdAndAddedUser(addedUser, commentId);
        return !record.isEmpty();
    }
    




    public ResponseEntity<Comment> updateComment(long id, Comment updatedComment) {
        Optional<Comment> commentData = commentRepository.findById(id);

        if (commentData.isPresent()) {
            Comment comment = commentData.get();
            comment.setComment(updatedComment.getComment()); // Update the comment field
            commentRepository.save(comment);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
