package io.github.wonyoungpark.ams.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by one0 on 2017. 7. 15..
 */
@Entity
@Data
public class User {
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
    @Enumerated(EnumType.STRING)
    private Role role;

}
