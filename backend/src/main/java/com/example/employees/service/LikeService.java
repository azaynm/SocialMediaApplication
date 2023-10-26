package com.example.employees.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.employees.model.Like;
import com.example.employees.repository.LikeRepository;

@Service
public class LikeService {
	
	@Autowired
    LikeRepository likeRepository;

	public ResponseEntity<Like> createLike(Like like) {
		try {
		      Like _like = likeRepository
		          .save(new Like(like.getPostId(), like.getUserId()));
		      return new ResponseEntity<>(_like, HttpStatus.CREATED);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		
	}

	public ResponseEntity<List<Like>> getAllLikes(Long id) {
		try {
		      List<Like> likes = new ArrayList<Like>();

		      if (id == null)
		    	  likeRepository.findAll().forEach(likes::add);
		      else
		    	  likeRepository.findByIdContaining(id).forEach(likes::add);

		      if (likes.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }

		      return new ResponseEntity<>(likes, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}

	public ResponseEntity<List<Like>> getSpecificPostLikes(long likeId) {
		try {
		      List<Like> likes = new ArrayList<Like>();
		    	  likeRepository.findByIdContaining(likeId).forEach(likes::add);
		      if (likes.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(likes, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	public ResponseEntity<Long> getNumOfPostLikes(long postId) {
		try {
		      
		    	 Long likes = likeRepository.findNumOfLikes(postId);
		      
		    	 return ResponseEntity.ok(likes);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}

	public ResponseEntity<HttpStatus> deleteLike(long id) {
		try {
	    	likeRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	public Long checkLike(Like like) {
	    List<Like> status = likeRepository.findLikeStatus(like.getUserId(), like.getPostId());
	    if(status.isEmpty()) {
	        return null;
	    } else {
	        return status.get(0).getId();
	    }
	}
    
    
    
}
