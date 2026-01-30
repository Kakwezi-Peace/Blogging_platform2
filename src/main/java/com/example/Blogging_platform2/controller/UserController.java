package com.example.Blogging_platform2.controller;

import com.example.Blogging_platform2.dto.ApiResponse;
import com.example.Blogging_platform2.dto.UserDto;
import com.example.Blogging_platform2.exception.UserNotFoundException;
import com.example.Blogging_platform2.model.User;
import com.example.Blogging_platform2.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "APIs for managing users (registration, retrieval, updates, deletion)")
public class UserController {

    // Inject the UserService to handle business logic
    // BEGINNER NOTE: Constructor injection is the recommended way
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @Operation(
        summary = "Register a new user",
        description = "Creates a new user account. Username and email must be unique."
    )
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "User registered successfully",
            content = @Content(schema = @Schema(implementation = UserDto.class))
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "400",
            description = "Validation error (missing fields, invalid email, etc.)"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "409",
            description = "Username or email already exists"
        )
    })
    public ResponseEntity<ApiResponse<UserDto>> registerUser(
            @Valid @RequestBody UserDto userDto) {
        
        // Convert DTO to Entity
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole() != null ? userDto.getRole() : "USER");
        
        // Save user through service
        User createdUser = userService.registerUser(user);
        
        // Convert Entity back to DTO (hide password)
        UserDto responseDto = convertToDto(createdUser);
        
        // Return success response with 201 Created status
        return new ResponseEntity<>(
            ApiResponse.success("User registered successfully", responseDto),
            HttpStatus.CREATED
        );
    }


    @GetMapping("/{username}")
    @Operation(
        summary = "Get user by username",
        description = "Retrieves a user's information by their username"
    )
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "User found",
            content = @Content(schema = @Schema(implementation = UserDto.class))
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "User not found"
        )
    })
    public ResponseEntity<ApiResponse<UserDto>> getUserByUsername(
            @Parameter(description = "Username of the user to retrieve")
            @PathVariable String username) {
        
        // Find user by username
        User user = userService.findByUsername(username);
        
        // If user not found, throw exception (handled by GlobalExceptionHandler)
        if (user == null) {
            throw new UserNotFoundException("User with username '" + username + "' not found");
        }
        
        // Convert to DTO and return
        return ResponseEntity.ok(
            ApiResponse.success("User retrieved successfully", convertToDto(user))
        );
    }


    @GetMapping
    @Operation(
        summary = "Get all users",
        description = "Retrieves a list of all registered users"
    )
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Users retrieved successfully"
        )
    })
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        // Get all users from service
        List<User> users = userService.findAll();
        
        // Convert all users to DTOs
        List<UserDto> userDtos = users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        
        // Return success response
        return ResponseEntity.ok(
            ApiResponse.success("Retrieved " + userDtos.size() + " users", userDtos)
        );
    }

    @GetMapping("/id/{id}")
    @Operation(
        summary = "Get user by ID",
        description = "Retrieves a user's information by their ID"
    )
    public ResponseEntity<ApiResponse<UserDto>> getUserById(
            @Parameter(description = "ID of the user to retrieve")
            @PathVariable Long id) {
        
        User user = userService.findById(id);
        
        if (user == null) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        
        return ResponseEntity.ok(
            ApiResponse.success("User retrieved successfully", convertToDto(user))
        );
    }


    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete user",
        description = "Deletes a user account by ID"
    )
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "User deleted successfully"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "User not found"
        )
    })
    public ResponseEntity<ApiResponse<Void>> deleteUser(
            @Parameter(description = "ID of the user to delete")
            @PathVariable Long id) {
        
        // Check if user exists
        User user = userService.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        
        // Delete user
        userService.deleteUser(id);
        
        // Return success response
        return ResponseEntity.ok(
            ApiResponse.success("User deleted successfully")
        );
    }

    // HELPER METHODS


    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setPassword(null);
        return dto;
    }
}
