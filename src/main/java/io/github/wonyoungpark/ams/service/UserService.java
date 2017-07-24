package io.github.wonyoungpark.ams.service;

import io.github.wonyoungpark.ams.domain.User;

import java.util.List;

/**
 * Created by one0 on 2017. 7. 15..
 */
public interface UserService {
    public User findByUsername(String name);
    public List<User> getUsers();
    public User getUser(Long id);
}
