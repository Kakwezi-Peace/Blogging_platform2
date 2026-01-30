package com.example.Blogging_platform2.graphqlcontroller;

import com.example.Blogging_platform2.model.Comment;
import com.example.Blogging_platform2.service.CommentService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CommentGraphQLController {

    private final CommentService service;

    public CommentGraphQLController(CommentService service) {
        this.service = service;
    }
// methods

    @QueryMapping
    public List<Comment> getCommentsByPost(@Argument int postId) {
        return service.getCommentsByPost(postId);
    }

    @QueryMapping
    public Comment getComment(@Argument int commentId) {
        return service.getComment(commentId);
    }

    @MutationMapping
    public Comment createComment(@Argument int postId,
                                 @Argument int userId,
                                 @Argument String content) {
        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setUserId(userId);
        comment.setContent(content);
        return service.createComment(comment);
    }


    @MutationMapping
    public boolean deleteComment(@Argument int commentId) {
        return service.deleteComment(commentId);
    }
}
