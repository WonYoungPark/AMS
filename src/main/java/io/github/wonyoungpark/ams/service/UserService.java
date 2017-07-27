package io.github.wonyoungpark.ams.service;

import io.github.wonyoungpark.ams.domain.User;
import io.github.wonyoungpark.ams.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by one0 on 2017. 7. 15..
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findOne(id);
    }

    public User createUser(User user) {

        String username = user.getUsername();
        if (userRepository.findByUsername(username) != null) {
            // TODO - 에러 처리
            log.error("user duplicated exception. {}", username);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
}
