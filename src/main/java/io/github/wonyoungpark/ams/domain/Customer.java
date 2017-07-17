package io.github.wonyoungpark.ams.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * 거래처
 * Created by one0 on 2017. 7. 1..
 */
@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    private String name; /// 이름

    private String address; // 주소

    private String telephone; // 전화번호

    private String useYn; // 사용여부

    private Date updatedDate; // 수정날짜

    @OneToMany
    private List<Ingredient> ingredients;
}
