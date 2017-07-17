package io.github.wonyoungpark.ams.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 매출
 * Created by one0 on 2017. 7. 1..
 */
@Data
@Entity
public class Sales {
    @Id
    @GeneratedValue
    private Long id;

    private String year; // 연도

    private String month; // 월

    private String day; // 일

    @Temporal(TemporalType.TIMESTAMP)
    private Date date; // 날짜

    @OneToOne
    private Food food; // 음식

    private int amount; // 수량
}
