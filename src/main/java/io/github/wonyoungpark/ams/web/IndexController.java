package io.github.wonyoungpark.ams.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by one0 on 2017. 7. 1..
 */
@RestController
public class IndexController {
    @RequestMapping("/")
    public String get() {
        return "asdadadad";
    }
}
