package io.github.wonyoungpark.ams.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 식재료
 * Created by one0 on 2017. 7. 1..
 */
@Data
@Entity
public class Ingredient {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Customer customer;

    @OneToMany
    private List<PriceOfIngredient> priceOfIngredients;
}
