package com.example.employees.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employees.model.Post;


public interface PostRepository extends JpaRepository<Post, Integer> {
	List<Post> findByTitleContaining(String title);
	
	}
