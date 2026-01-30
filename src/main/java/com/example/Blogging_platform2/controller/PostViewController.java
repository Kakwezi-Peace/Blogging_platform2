package com.example.Blogging_platform2.controller;

import com.example.Blogging_platform2.dto.PostViewDto;
import com.example.Blogging_platform2.model.PostView;
import com.example.Blogging_platform2.service.PostViewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/views")
public class PostViewController {

    private final PostViewService service;

    public PostViewController(PostViewService service) {
        this.service = service;
    }

    @PostMapping
    public PostView createView(@RequestBody PostViewDto dto) {
        PostView view = new PostView();
        view.setPostId(dto.getPostId());
        view.setUserId(dto.getUserId());
        return service.createView(view);
    }

    @GetMapping("/post/{postId}")
    public List<PostView> getViewsByPost(@PathVariable int postId) {
        return service.getViewsByPost(postId);
    }

    @GetMapping("/{id}")
    public PostView getView(@PathVariable int id) {
        return service.getView(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteView(@PathVariable int id) {
        return service.deleteView(id);
    }
}
