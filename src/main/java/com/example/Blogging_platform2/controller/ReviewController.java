package com.example.Blogging_platform2.controller;

import com.example.Blogging_platform2.dto.ReviewDto;
import com.example.Blogging_platform2.dto.ReviewDto;
import com.example.Blogging_platform2.model.Review;
import com.example.Blogging_platform2.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")

public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping
    public Review createReview(@RequestBody ReviewDto dto) {
        Review review = new Review();
        review.setPostId(dto.getPostId());
        review.setUserId(dto.getUserId());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        return service.createReview(review);
    }

    @GetMapping("/post/{postId}")
    public List<Review> getReviewsByPost(@PathVariable int postId) {
        return service.getReviewsByPost(postId);
    }

    @GetMapping("/{id}")
    public Review getReview(@PathVariable int id) {
        return service.getReview(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteReview(@PathVariable int id) {
        return service.deleteReview(id);
    }
}
