package com.example.Blogging_platform2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * BEGINNER-FRIENDLY API RESPONSE WRAPPER
 * 
 * This class wraps all API responses in a consistent format.
 * Every API endpoint will return data in this structure:
 * {
 *   "status": "success" or "error",
 *   "message": "Description of what happened",
 *   "data": { ... actual data ... },
 *   "timestamp": "2026-01-27T13:00:00"
 * }
 * 
 * Why use this?
 * - Consistent response format across all endpoints
 * - Easy for frontend developers to handle responses
 * - Clear success/error indication
 * 
 * @param <T> The type of data being returned (User, Post, List<Post>, etc.)
 */
public class ApiResponse<T> {
    
    // Status of the response: "success" or "error"
    private String status;
    
    // Human-readable message describing what happened
    private String message;
    
    // The actual data being returned (can be any type)
    private T data;
    
    // When this response was created
    private LocalDateTime timestamp;
    
    // CONSTRUCTORS
    
    /**
     * Default constructor (required for JSON serialization)
     */
    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }
    
    /**
     * Full constructor with all fields
     */
    public ApiResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }
    
    // HELPER METHODS FOR CREATING RESPONSES
    
    /**
     * Create a successful response with data
     * Example: ApiResponse.success("User created successfully", user)
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>("success", message, data);
    }
    
    /**
     * Create a successful response without data
     * Example: ApiResponse.success("Post deleted successfully")
     */
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>("success", message, null);
    }
    
    /**
     * Create an error response
     * Example: ApiResponse.error("User not found")
     */
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>("error", message, null);
    }
    
    /**
     * Create an error response with data (e.g., validation errors)
     * Example: ApiResponse.error("Validation failed", validationErrors)
     */
    public static <T> ApiResponse<T> error(String message, T data) {
        return new ApiResponse<>("error", message, data);
    }
    
    // GETTERS AND SETTERS
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    public static class ActivityLogDto {
        private Integer userId;

        @NotBlank(message = "Action cannot be empty")
        private String action;

        private Integer targetId;
        private String details;
    }
}
