package com.example.Blogging_platform2.validation;

import com.example.Blogging_platform2.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {


    @Autowired
    private UserRepository userRepository;
    
    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }
    
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        if (email == null || email.trim().isEmpty()) {
            return true;
        }

        return userRepository.findByEmail(email) == null;
    }
}
