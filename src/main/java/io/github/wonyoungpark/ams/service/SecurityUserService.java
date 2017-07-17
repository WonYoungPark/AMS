package io.github.wonyoungpark.ams.service;

import io.github.wonyoungpark.ams.domain.SecurityUser;
import io.github.wonyoungpark.ams.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by one0 on 2017. 7. 15..
 */
@Service
public class SecurityUserService implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        return new SecurityUser(user);
    }
}
