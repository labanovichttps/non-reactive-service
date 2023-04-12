package com.labanovich.product.repostitory;

import com.labanovich.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findAllByPriceBetween(Integer minPrice, Integer maxPrice);
}
