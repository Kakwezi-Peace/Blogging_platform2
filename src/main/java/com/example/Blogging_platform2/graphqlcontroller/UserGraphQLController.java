package com.example.Blogging_platform2.graphqlcontroller;

import com.example.Blogging_platform2.model.User;
import com.example.Blogging_platform2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
@AllArgsConstructor
public class UserGraphQLController {

    private final UserService service;


    @QueryMapping
    public List<User> getAllUsers() {
        return service.findAll();
    }

    @QueryMapping
    public User getUser(@Argument Long id) {
        return service.findById(id);
    }

    @MutationMapping
    public User createUser(@Argument String username,
                           @Argument String email,
                           @Argument String password) {
        return service.registerUser(new User(null, username, email, password, "USER"));
    }
}
