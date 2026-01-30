package graphqlcontroller;

import com.example.Blogging_platform2.dto.CreatePostRequest;
import com.example.Blogging_platform2.model.Post;
import com.example.Blogging_platform2.service.PostService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BlogGraphQLController {

    private final PostService postService;

    public BlogGraphQLController(PostService postService) {
        this.postService = postService;
    }

    @QueryMapping
    public List<Post> allPosts() {
        return postService.getAllPosts();
    }

    @QueryMapping
    public Post postById(@Argument int id) {
        return postService.getPostById(id, null);
    }

    @MutationMapping
    public Post createPost(@Argument CreatePostRequest input) {
        Post post = new Post();
        post.setTitle(input.getTitle());
        post.setContent(input.getTitle());
        return postService.createPost(post, input.getUserId());
    }
}
