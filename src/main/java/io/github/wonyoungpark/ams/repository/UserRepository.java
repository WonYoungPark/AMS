package io.github.wonyoungpark.ams.repository;

import io.github.wonyoungpark.ams.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by one0 on 2017. 7. 15..
 */
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
