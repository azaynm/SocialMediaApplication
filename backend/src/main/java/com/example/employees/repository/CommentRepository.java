package com.example.employees.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.employees.model.Comment;
import com.example.employees.model.Like;


public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByIdContaining(long id);
	List<Comment> findByPostId(long postId);
	
	
	@Query(value = "SELECT * FROM likes WHERE username LIKE ':username' AND id = : commentId ", nativeQuery = true)
    List<Like> checkCommentedUser(@Param("username") String username, @Param("commentId") long commentId);
	
	
	@Query(value = "SELECT * FROM comments WHERE added_user = :added_user AND id = :id ", nativeQuery = true)
    List<Comment> findByCommentIdAndAddedUser(@Param("added_user") long added_user, @Param("id") long id);
	
	}

