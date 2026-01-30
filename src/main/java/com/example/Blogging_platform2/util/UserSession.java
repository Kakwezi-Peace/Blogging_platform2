package com.example.Blogging_platform2.util;

import com.example.Blogging_platform2.model.User;
import lombok.Getter;

public class UserSession {

    @Getter
    private static UserSession instance;

    private User user;

    private UserSession(User user) {
        this.user = user;
    }

    public static void setSession(User user) {
        instance = new UserSession(user);
    }
    
    public static void clearSession() {
        instance = null;
    }

    public User getUser() {
        return user;
    }
}
