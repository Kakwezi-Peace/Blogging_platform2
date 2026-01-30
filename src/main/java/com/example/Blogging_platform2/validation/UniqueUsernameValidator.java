package com.example.Blogging_platform2.validation;

import com.example.Blogging_platform2.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {

    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {

        if (username == null || username.trim().isEmpty()) {
            return true;
        }
        

        return userRepository.findByUsername(username) == null;
    }
}
