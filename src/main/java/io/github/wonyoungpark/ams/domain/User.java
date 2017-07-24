package io.github.wonyoungpark.ams.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

/**
 * Created by one0 on 2017. 7. 15..
 */
@Entity
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean isEnabled;

    @Column(nullable = false)
    private String role;

    /**
     * 사용자에게 권한을 부여함
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<GrantedAuthority> authorities = new HashSet<>(1);
//        String role = member.getMemberAuth().getRole().toString();
//        authorities.add(new SimpleGrantedAuthority(role));
//        return authorities;
//        return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));

//        Collection<GrantedAuthority> authorities = new HashSet<>();
//        authorities.add(new SimpleGrantedAuthority("ADMIN"));

//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        return authorities;

//        Set<String> roles = new HashSet<>();
//        roles.add("ROLE_USER");
//        roles.add("ROLE_ADMIN");
//        roles.add("ROLE_SUPER");
//        Collection<GrantedAuthority> authorities = new HashSet<>();
//        for(String role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role));
//        }
//        return authorities;

        Collection<GrantedAuthority> authorities = new HashSet<>();
        String roles[] = role.toUpperCase().split(", ");
        for(String role : roles) {
            authorities.add((new SimpleGrantedAuthority("ROLE_" + role)));
        }
        return authorities;
    }

    /**
     * 계정이 만료되지 않았다는 것을 반환
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정이 잠겨 있지 않다는 것을 반환
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 자격이 유효하다는 것을 반환
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계정이 활성화되어 있다는 것을 반환
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
