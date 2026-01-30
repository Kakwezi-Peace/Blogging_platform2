package com.example.Blogging_platform2.service;

import com.example.Blogging_platform2.exception.ReviewNotFoundException;
import com.example.Blogging_platform2.model.Review;
import com.example.Blogging_platform2.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository repo;

    public ReviewService(ReviewRepository repo) {
        this.repo = repo;
    }

    public List<Review> getReviewsByPost(int postId) {
        return repo.findAllByPostId(postId);
    }

    public Review getReview(int id) {
        Review review = repo.findById(id);
        if (review == null) {
            throw new ReviewNotFoundException(id);
        }
        return review;
    }


    public Review createReview(Review review) {
        review.setCreatedAt(LocalDateTime.now());
        return repo.save(review);
    }

    public boolean deleteReview(int id) {
        return repo.delete(id) > 0;
    }
}
