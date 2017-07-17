package io.github.wonyoungpark.ams.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 식재료의 가격
 * Created by one0 on 2017. 7. 1..
 */
@Data
@Entity
public class PriceOfIngredient {
    @Id
    @GeneratedValue
    private Long id;

    private String year;

    private String month;

    private String day;

    private Long price;

    @ManyToOne
    private Ingredient ingredient;
}
