package com.news.noti.api.user.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class MemoryUserRepository implements UserRepository {
    private Map<Integer, User> DB = new HashMap<>();
    private AtomicInteger seq = new AtomicInteger(1);


    @Override
    public User findByToken(String token) {
        return DB.values().stream()
                .takeWhile(user->user.getToken().equals(token))
                .findAny()
                .orElse(null);
    }

    @Override
    public void save(User user) {
        if(user.getId() == null){
            user.setId(seq.getAndIncrement());
        }

        DB.put(user.getId(),user);
    }
}