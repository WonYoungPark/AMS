package io.github.wonyoungpark.ams.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by one0 on 2017. 7. 1..
 */
@Controller
public class IndexController {
    final String path = "views/auth/";

    @GetMapping("/")
    public String index() {
        return path + "index";
    }

    @GetMapping("/login2")
    public String login() {
        return path + "login2";
    }
}
