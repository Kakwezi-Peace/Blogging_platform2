package com.example.Blogging_platform2.controller;

import com.example.Blogging_platform2.dto.ApiResponse;
import com.example.Blogging_platform2.dto.PostDto;
import com.example.Blogging_platform2.exception.PostNotFoundException;
import com.example.Blogging_platform2.model.Post;
import com.example.Blogging_platform2.repository.PostRepository;
import com.example.Blogging_platform2.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
@Tag(name = "Post Management", description = "APIs for managing blog posts (create, read, update, delete, search)")
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    public PostController(PostService postService, PostRepository postRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
    }


    @GetMapping
    @Operation(
        summary = "Get all posts",
        description = "Retrieves all blog posts with pagination and sorting support. " +
                     "Use page and size for pagination, sort for ordering."
    )
    public ResponseEntity<ApiResponse<List<PostDto>>> getAllPosts(
            @Parameter(description = "Page number (starts from 0)")
            @RequestParam(defaultValue = "0") int page,
            
            @Parameter(description = "Number of posts per page")
            @RequestParam(defaultValue = "10") int size,
            
            @Parameter(description = "Sort field: created_at, title, or updated_at")
            @RequestParam(defaultValue = "created_at") String sort) {

        // Get posts with pagination from repository
        List<Post> posts = postRepository.findAll(page, size, sort);
        
        // Convert to DTOs
        List<PostDto> postDtos = posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        
        // Return success response
        return ResponseEntity.ok(
            ApiResponse.success(
                "Retrieved " + postDtos.size() + " posts (page " + page + ")", 
                postDtos
            )
        );
    }


    @GetMapping("/{id}")
    @Operation(
        summary = "Get post by ID",
        description = "Retrieves a single blog post by its ID. Optionally tracks the view if userId is provided."
    )
    public ResponseEntity<ApiResponse<PostDto>> getPostById(
            @Parameter(description = "ID of the post to retrieve")
            @PathVariable int id,
            
            @Parameter(description = "User ID (optional, for tracking views)")
            @RequestParam(required = false) Integer userId) {
        
        // Get post from service (this also logs the view if userId is provided)
        Post post = postService.getPostById(id, userId);
        
        // Return success response
        return ResponseEntity.ok(
            ApiResponse.success("Post retrieved successfully", convertToDto(post))
        );
    }


    @PostMapping
    @Operation(
        summary = "Create a new post",
        description = "Creates a new blog post. The userId must be provided as a query parameter."
    )
    public ResponseEntity<ApiResponse<PostDto>> createPost(
            @Valid @RequestBody PostDto postDto,
            
            @Parameter(description = "ID of the user creating the post", required = true)
            @RequestParam int userId) {
        
        // Convert DTO to Entity
        Post post = convertToEntity(postDto);
        
        // Create post through service
        Post createdPost = postService.createPost(post, userId);
        
        // Return success response with 201 Created status
        return new ResponseEntity<>(
            ApiResponse.success("Post created successfully", convertToDto(createdPost)),
            HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Update a post",
        description = "Updates an existing blog post. Only the post owner can update it."
    )
    public ResponseEntity<ApiResponse<PostDto>> updatePost(
            @Parameter(description = "ID of the post to update")
            @PathVariable int id,
            
            @Valid @RequestBody PostDto postDto,
            
            @Parameter(description = "ID of the user updating the post", required = true)
            @RequestParam int userId) {
        
        // Convert DTO to Entity
        Post post = convertToEntity(postDto);
        post.setId(id);
        
        // Update post through service
        postService.updatePost(id, post, userId);
        
        // Get updated post
        Post updatedPost = postService.getPostById(id, null);
        
        // Return success response
        return ResponseEntity.ok(
            ApiResponse.success("Post updated successfully", convertToDto(updatedPost))
        );
    }


    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a post",
        description = "Deletes a blog post. Only the post owner or admin can delete it."
    )
    public ResponseEntity<ApiResponse<Void>> deletePost(
            @Parameter(description = "ID of the post to delete")
            @PathVariable int id,
            
            @Parameter(description = "ID of the user deleting the post", required = true)
            @RequestParam int userId) {
        
        // Delete post through service
        postService.deletePost(id, userId);
        
        // Return success response
        return ResponseEntity.ok(
            ApiResponse.success("Post deleted successfully")
        );
    }

    @GetMapping("/search")
    @Operation(
        summary = "Search posts",
        description = "Searches for posts by title or content containing the query string."
    )
    public ResponseEntity<ApiResponse<List<PostDto>>> searchPosts(
            @Parameter(description = "Search query", required = true)
            @RequestParam String query) {
        
        // Get all posts
        List<Post> allPosts = postRepository.findAll();
        
        // Filter posts that contain the query in title or content
        List<Post> matchingPosts = allPosts.stream()
                .filter(post -> 
                    post.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    post.getContent().toLowerCase().contains(query.toLowerCase())
                )
                .collect(Collectors.toList());
        
        // Convert to DTOs
        List<PostDto> postDtos = matchingPosts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        
        // Return success response
        return ResponseEntity.ok(
            ApiResponse.success(
                "Found " + postDtos.size() + " posts matching '" + query + "'", 
                postDtos
            )
        );
    }


    private PostDto convertToDto(Post post) {
        return new PostDto(
            post.getId(),
            post.getUserId(),
            post.getTitle(),
            post.getContent(),
            post.getCreatedAt(),
            post.getUpdatedAt()

        );
    }

    private Post convertToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        return post;
    }
}
