package io.github.wonyoungpark.ams.config;

import io.github.wonyoungpark.ams.domain.User;
import io.github.wonyoungpark.ams.repository.UserRepository;
import io.github.wonyoungpark.ams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.hibernate.criterion.Restrictions.and;

/**
 * Created by one0 on 2017. 7. 15..
 */
@Configuration
@EnableWebSecurity // 웹보안 설정 : WebSecurityConfigurerAdapter를 상속받은 클래스에 선언되어야 함.
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static String LOGIN_API = "/api/login";
    private static String LOGOUT_API = "/api/logout";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    /**
     * userDetailsService와 passwordEncoder를 AuthenticationManagerBuilder에 세팅해 준다.(사용자의 username과 password가 맞는지 확인한다.)
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() { // 사용자 정의 UserDetailService

            /**
             * Spring-Security에서 User 정보를 조회하기 위한 메서드
             * @param username
             * @return
             * @throws UsernameNotFoundException
             */
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userService.findByUsername(username);
                if(user == null) {
                    throw new UsernameNotFoundException("회원을 찾을 수 없습니다");
                } else {
                    return user;
                }
            }
        }).passwordEncoder(passwordEncoder);
    }

    /**
     * 특정 요청에 대해서 시큐리티 설정을 무시하함.
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/font-awesome/**", "/fonts/**", "/img/**", "/js/**", "/webjars/**", "/h2console/**");
    }

    /**
     * Http 설정
     * 설명 : 인가, 로그인, 로그아웃을 설정한다.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // we don't need CSRF because our token is invulnerable
            .csrf().disable()
            // don't create session
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .exceptionHandling()
               .authenticationEntryPoint(unauthorizedHandler) // 인증되지 않은 Request를 처리
        .and()
            .authorizeRequests()
            .antMatchers("/**").hasRole("USER")
            .antMatchers("/user/**").hasAnyRole("USER", "ADMIN", "SUPER")
            .antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPER")
            .antMatchers("/super/**").hasRole("SUPER")
            .antMatchers(HttpMethod.POST, LOGIN_API).permitAll()
            .antMatchers(LOGOUT_API).permitAll()
        .and()
            .formLogin()
            .defaultSuccessUrl("/adadadasdadadad", true) // 인증 성공시 이동경로
            .successHandler(authenticationSuccessHandler) // 인증이 성공한 경우
            .failureHandler(authenticationFailureHandler) // 인증이 실패한 경우
            .usernameParameter("username")
            .passwordParameter("password")
            .permitAll()
        .and()
            .addFilterBefore(jwtAuthenticationTokenFilter(), BasicAuthenticationFilter.class)
        .and()
            .logout().permitAll().logoutSuccessUrl("/logout");



    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
