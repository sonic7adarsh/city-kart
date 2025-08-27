package com.citykart.auth.context;

import com.citykart.user.entity.User;

public class UserContextHolder {
    private static final ThreadLocal<User> context = new ThreadLocal<>();

    public static void set(User user) {
        context.set(user);
    }

    public static User get() {
        return context.get();
    }

    public static void clear() {
        context.remove();
    }
}
