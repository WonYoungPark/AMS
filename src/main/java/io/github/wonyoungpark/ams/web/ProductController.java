package io.github.wonyoungpark.ams.web;

import io.github.wonyoungpark.ams.domain.Product;
import io.github.wonyoungpark.ams.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by one0 on 2017. 7. 1..
 */
@RestController
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(repository.findAll());
    }
}
