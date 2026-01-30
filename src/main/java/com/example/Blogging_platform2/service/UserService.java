package com.example.Blogging_platform2.service;

import com.example.Blogging_platform2.model.User;
import com.example.Blogging_platform2.repository.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }




    public User registerUser(User user) {
        userRepository.save(user);
        return userRepository.findByUsername(user.getUsername());
    }

    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }
    
    public User findById(Long id) {

        return userRepository.findById(id);
    }

    public List<User> findAll() {

        return userRepository.findAll();
    }

    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }
}
