package io.github.wonyoungpark.ams.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by one0 on 2017. 7. 15..
 */
@Configuration
@EnableJpaRepositories(basePackages = "io.github.wonyoungpark.ams.repository")
public class AppConfig extends WebMvcConfigurerAdapter {

    /**
     * 로그인 페이지용 뷰를 매핑한다.
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 해당 경로를 login템플릿으로 매핑
        registry.addViewController("/login").setViewName("login");
    }
//
//    /**
//     * 리졸버 추가 - 컨트롤러 매개변수를 처리할 수 있도록 처리
//     * @param argumentResolvers
//     */
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        // 타입의 객체가 컨트롤러 매개변수로 있을 떄 처리할 리졸버 설정
//        argumentResolvers.add(new UserHandlerMethodArgumentResolver());
//    }ㅍ
}
