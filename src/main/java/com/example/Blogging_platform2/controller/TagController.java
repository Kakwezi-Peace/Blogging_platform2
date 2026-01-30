package com.example.Blogging_platform2.controller;

import com.example.Blogging_platform2.dto.TagDto;
import com.example.Blogging_platform2.model.Tag;
import com.example.Blogging_platform2.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService service;

    public TagController(TagService service) {
        this.service = service;
    }

    @PostMapping
    public Tag createTag(@RequestBody TagDto dto) {
        Tag tag = new Tag();
        tag.setName(dto.getName());
        return service.createTag(tag);
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return service.getAllTags();
    }

    @GetMapping("/{id}")
    public Tag getTag(@PathVariable int id) {
        return service.getTag(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTag(@PathVariable int id) {
        return service.deleteTag(id);
    }
}

//