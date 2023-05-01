package com.news.noti.api.user.domain;


import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Integer> {
    User findByToken(String token);
    void save(User user);
}
