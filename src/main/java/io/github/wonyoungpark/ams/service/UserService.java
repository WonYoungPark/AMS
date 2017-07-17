package io.github.wonyoungpark.ams.service;

import io.github.wonyoungpark.ams.domain.User;

/**
 * Created by one0 on 2017. 7. 15..
 */
public interface UserService {
    public User getUserByUsername(String name);
}
