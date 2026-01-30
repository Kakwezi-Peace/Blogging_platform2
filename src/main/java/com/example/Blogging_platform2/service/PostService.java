package com.example.Blogging_platform2.service;

import com.example.Blogging_platform2.exception.ResourceNotFoundException;
import com.example.Blogging_platform2.model.Post;
import com.example.Blogging_platform2.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private final PostRepository repo;

    public PostService(PostRepository repo) {
        this.repo = repo;
    }

    public List<Post> getAllPosts() {
        return repo.findAll();
    }

    @Transactional
    public Post getPostById(int id, Integer userId) {
        Post post = repo.findById(id);
        if (post == null) {
            throw new ResourceNotFoundException("Post not found with id: " + id);
        }
        return post;
    }


    @Transactional
    public Post createPost(Post post, int userId) {
        System.out.println("Post:   -------------------"+post);
        post.setUserId(userId);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        repo.save(post);
        return post;
    }

    @Transactional
    public void updatePost(int id, Post updatedPost, int userId) {
        Post existingPost = repo.findById(id);
        if (existingPost == null) {
            throw new ResourceNotFoundException("Post not found with id: " + id);
        }
        if (existingPost.getUserId() != userId) {
            throw new RuntimeException("Unauthorized to update this post");
        }
        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setContent(updatedPost.getContent());
        existingPost.setUpdatedAt(LocalDateTime.now());
        repo.update(existingPost);
    }

    @Transactional
    public void deletePost(int id, int userId) {
        Post post = repo.findById(id);
        if (post == null) {
            throw new ResourceNotFoundException("Post not found with id: " + id);
        }
        if (post.getUserId() != userId) {
            throw new RuntimeException("Unauthorized to delete this post");
        }
        repo.deleteById(id);
    }
}
