package com.selenium.entities;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<User> {
    private Set<User> delegate;

    public Users(Users users) {
        this.delegate = new HashSet<>(users.delegate);
    }

    public Users() {
        this.delegate = new HashSet<>();
    }

    public Users(Collection<User> users) {
        this.delegate = new HashSet<>(users);
    }

    @Override
    protected Set<User> delegate() {
        return delegate;
    }

    public User getUser(String role) {
        for (User user : delegate) {
            if (user.getRole().equals(role)) {
                return user;
            }
        }
        throw new NullPointerException("Undefined user role: " + role);
    }
}
