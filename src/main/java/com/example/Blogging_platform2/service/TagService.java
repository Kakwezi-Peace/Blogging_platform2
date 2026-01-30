package com.example.Blogging_platform2.service;

import com.example.Blogging_platform2.exception.TagNotFoundException;
import com.example.Blogging_platform2.model.Tag;
import com.example.Blogging_platform2.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository repo;

    public TagService(TagRepository repo) {
        this.repo = repo;
    }

    public List<Tag> getAllTags() {
        return repo.findAll();
    }

    public Tag getTag(int id) {
        Tag tag = repo.findById(id);
        if (tag == null) {
            throw new TagNotFoundException(id);
        }
        return tag;
    }

    public Tag createTag(Tag tag) {
        return repo.save(tag);
    }

    public boolean deleteTag(int id) {
        return repo.delete(id) > 0;
    }
}
