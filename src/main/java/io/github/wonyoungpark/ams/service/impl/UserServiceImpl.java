package io.github.wonyoungpark.ams.service.impl;

import io.github.wonyoungpark.ams.domain.User;
import io.github.wonyoungpark.ams.repository.UserRepository;
import io.github.wonyoungpark.ams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by one0 on 2017. 7. 15..
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findOne(id);
    }
}
