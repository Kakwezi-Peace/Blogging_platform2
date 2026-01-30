package com.example.Blogging_platform2.graphqlcontroller;

import com.example.Blogging_platform2.model.PostView;
import com.example.Blogging_platform2.service.PostViewService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PostViewGraphQLController {

    private final PostViewService service;

    public PostViewGraphQLController(PostViewService service) {
        this.service = service;
    }

    @QueryMapping
    public List<PostView> getViewsByPost(@Argument int postId) {
        return service.getViewsByPost(postId);
    }

    @QueryMapping
    public PostView getView(@Argument int id) {
        return service.getView(id);
    }
// methods

    @MutationMapping
    public PostView createView(@Argument int postId, @Argument Integer userId) {
        PostView view = new PostView();
        view.setPostId(postId);
        view.setUserId(userId);
        return service.createView(view);
    }
 // mutation
    @MutationMapping
    public boolean deleteView(@Argument int id) {
        return service.deleteView(id);
    }
}
