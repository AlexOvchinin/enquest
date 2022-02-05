package com.farmean.enquest.services.users;

import com.farmean.enquest.models.User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryUserService implements UsersService {
    private final Map<Long, User> users = new ConcurrentHashMap<>();

    @Override
    public User getUser(long id) {
        return users.computeIfAbsent(id, key -> new User(id));
    }
}
