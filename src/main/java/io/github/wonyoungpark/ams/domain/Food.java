package io.github.wonyoungpark.ams.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 음식
 * Created by one0 on 2017. 7. 1..
 */
@Entity
@Data
public class Food {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne
    private PriceOfFood priceOfFood;
}
