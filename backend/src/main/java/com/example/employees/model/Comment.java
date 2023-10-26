package com.example.employees.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "postId")
	private long postId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "userImage")
	private String userImage;

	@Column(name = "added_user")
	private long added_user;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "time")
	private String time;

	
	public Comment() {

	}


	public Comment(long postId, String username, String userImage, long added_user, String comment, String time) {
		this.postId = postId;
		this.username = username;
		this.userImage = userImage;
		this.added_user = added_user;
		this.comment = comment;
		this.time = time;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getPostId() {
		return postId;
	}


	public void setPostId(long postId) {
		this.postId = postId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getUserImage() {
		return userImage;
	}


	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}


	public long getAdded_user() {
		return added_user;
	}


	public void setAdded_user(long added_user) {
		this.added_user = added_user;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	



	
	
	

	

}

