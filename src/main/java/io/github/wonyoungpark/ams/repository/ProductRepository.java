package io.github.wonyoungpark.ams.repository;

import io.github.wonyoungpark.ams.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by one0 on 2017. 7. 1..
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
}
