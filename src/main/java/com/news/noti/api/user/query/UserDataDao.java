package com.news.noti.api.user.query;

import com.news.noti.api.user.domain.User;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserDataDao extends Repository<UserData, Integer> {
    List<UserData> findAll();
}
