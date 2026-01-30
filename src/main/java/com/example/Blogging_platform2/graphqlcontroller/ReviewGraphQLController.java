package com.example.Blogging_platform2.graphqlcontroller;

import com.example.Blogging_platform2.model.Review;
import com.example.Blogging_platform2.service.ReviewService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ReviewGraphQLController {
// DI
    private final ReviewService service;

    public ReviewGraphQLController(ReviewService service) {
        this.service = service;
    }

    @QueryMapping
    public List<Review> getReviewsByPost(@Argument int postId) {
        return service.getReviewsByPost(postId);
    }

    @QueryMapping
    public Review getReview(@Argument int reviewId) {
        return service.getReview(reviewId);
    }

    @MutationMapping
    public Review createReview(@Argument int postId,
                               @Argument int userId,
                               @Argument int rating,
                               @Argument String comment) {
        Review review = new Review();
        review.setPostId(postId);
        review.setUserId(userId);
        review.setRating(rating);
        review.setComment(comment);
        return service.createReview(review);
    }

    @MutationMapping
    public boolean deleteReview(@Argument int reviewId) {
        return service.deleteReview(reviewId);
    }
}
