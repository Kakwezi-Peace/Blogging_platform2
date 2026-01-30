package com.example.Blogging_platform2.graphqlcontroller;

import com.example.Blogging_platform2.model.Tag;
import com.example.Blogging_platform2.service.TagService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TagGraphQLController {

    private final TagService service;

    public TagGraphQLController(TagService service) {
        this.service = service;
    }

    @QueryMapping
    public List<Tag> getAllTags() {
        return service.getAllTags();
    }

    @QueryMapping
    public Tag getTag(@Argument int tagId) {
        return service.getTag(tagId);
    }

    @MutationMapping
    public Tag createTag(@Argument String name) {
        Tag tag = new Tag();
        tag.setName(name);
        return service.createTag(tag);
    }
// mutation mapping
    @MutationMapping
    public boolean deleteTag(@Argument int tagId) {
        return service.deleteTag(tagId);
    }
}
