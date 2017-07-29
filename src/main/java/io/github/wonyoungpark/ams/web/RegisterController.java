package io.github.wonyoungpark.ams.web;

import io.github.wonyoungpark.ams.domain.User;
import io.github.wonyoungpark.ams.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/register")
public class RegisterController {

    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public void postRegister(User user) {
        //User user = userService
        //userService.createUser(user);
    }
}
