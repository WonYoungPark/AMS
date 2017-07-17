package io.github.wonyoungpark.ams.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by one0 on 2017. 7. 15..
 */
@Configuration
@EnableJpaRepositories(basePackages = "io.github.wonyoungpark.ams.repository")
public class AppConfig {
}
