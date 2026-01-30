package com.example.Blogging_platform2.service;

import com.example.Blogging_platform2.exception.PostViewNotFoundException;
import com.example.Blogging_platform2.model.PostView;
import com.example.Blogging_platform2.repository.PostViewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostViewService {

    private final PostViewRepository repo;

    public PostViewService(PostViewRepository repo) {
        this.repo = repo;
    }

    public List<PostView> getViewsByPost(int postId) {
        return repo.findByPostId(postId);
    }

    public PostView getView(int id) {
        PostView view = repo.findById(id);
        if (view == null) {
            throw new PostViewNotFoundException(id);
        }
        return view;
    }


    public PostView createView(PostView view) {
        view.setViewedAt(LocalDateTime.now());
        return repo.save(view);
    }

    public boolean deleteView(int id) {
        return repo.delete(id) > 0;
    }
}
