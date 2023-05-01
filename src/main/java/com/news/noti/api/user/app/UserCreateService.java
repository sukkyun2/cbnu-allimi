package com.news.noti.api.user.app;

import com.news.noti.api.user.domain.User;
import com.news.noti.api.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreateService {
    private final UserRepository userRepository;

    public void join(UserCreateRequest req) {
        req.validate();

        User byToken = userRepository.findByToken(req.getToken());

        if (byToken != null) {
            throw new UserAlreadyExistsException();
        }

        userRepository.save(User.ofToken(req.getToken()));
    }
}
