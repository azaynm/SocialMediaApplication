package com.example.employees.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.employees.model.Post;
import com.example.employees.repository.PostRepository;

import jakarta.transaction.Transactional;

@Service
public class PostService {
	@Autowired
    private PostRepository postRepository;
	
	private final String STORAGE_LOCATION = "D:\\PAF\\frontend\\public\\uploads\\";
	
	public List<Post> getAllPosts(String title) {
	    List<Post> posts = new ArrayList<Post>();
	    if (title == null) {
	        postRepository.findAll().forEach(posts::add);
	    } else {
	        postRepository.findByTitleContaining(title).forEach(posts::add);
	    }
	    return posts;
	}
	
	public void deletePostById(Integer id) {
        postRepository.deleteById(id);
    }
	
	public Post createPost(String userImage, String userId, MultipartFile postImage, String title) throws IOException {
        String postImagePath = null;
        if (postImage != null && !postImage.isEmpty()) {
            File path2 = new File(STORAGE_LOCATION + postImage.getOriginalFilename());
            path2.createNewFile();
            FileOutputStream output2 = new FileOutputStream(path2);
            output2.write(postImage.getBytes());
            output2.close();
            postImagePath = postImage.getOriginalFilename();
        }

        Post post = new Post(userImage, userId, postImagePath, title);
        return postRepository.save(post);
    }

    @Transactional
    public void deletePost(Integer postId) {
        postRepository.deleteById(postId);
    }
}
