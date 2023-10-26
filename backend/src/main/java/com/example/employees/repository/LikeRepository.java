package com.example.employees.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import com.example.employees.model.Comment;
import com.example.employees.model.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
	List<Like> findByIdContaining(long id);
	
	@Query(value = "SELECT * FROM likes WHERE user_id = :userId AND post_id = :postId ", nativeQuery = true)
    List<Like> findLikeStatus(@Param("userId") Long userId, @Param("postId") Long postId);
	
	
	@Query(value = "SELECT COUNT(*) FROM likes WHERE post_id = :postId ", nativeQuery = true)
    Long findNumOfLikes(@Param("postId") Long postId);
}
