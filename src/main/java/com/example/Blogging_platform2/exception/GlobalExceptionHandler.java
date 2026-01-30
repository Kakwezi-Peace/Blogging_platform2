package com.example.Blogging_platform2.exception;

import com.example.Blogging_platform2.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        
        // Create a map to store all validation errors
        Map<String, String> errors = new HashMap<>();
        
        // Loop through all validation errors and add them to the map
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        
        // Return error response with validation errors
        return new ResponseEntity<>(
            ApiResponse.error("Validation failed", errors),
            HttpStatus.BAD_REQUEST  // 400 status code
        );
    }

    @ExceptionHandler({
        ResourceNotFoundException.class,
        UserNotFoundException.class,
        PostNotFoundException.class,
        CommentNotFoundException.class,
        TagNotFoundException.class,
        ReviewNotFoundException.class
    })
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFound(RuntimeException ex) {
        return new ResponseEntity<>(
            ApiResponse.error(ex.getMessage()),
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleDataIntegrityViolation(
            org.springframework.dao.DataIntegrityViolationException ex) {

        String message = "Database constraint violation";
        if (ex.getRootCause() != null) {
            message = "Database error: " + ex.getRootCause().getMessage();
        }
        
        return new ResponseEntity<>(
            ApiResponse.error(message),
            HttpStatus.CONFLICT  // 409 status code
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgument(IllegalArgumentException ex) {
        return new ResponseEntity<>(
            ApiResponse.error(ex.getMessage()),
            HttpStatus.BAD_REQUEST  // 400 status code
        );
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(
            ApiResponse.error("An unexpected error occurred: " + ex.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR  // 500 status code
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex) {
        return new ResponseEntity<>(
            ApiResponse.error("An error occurred: " + ex.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR  // 500 status code
        );
    }
}
