package com.labanovich.product.service;

import com.labanovich.product.dto.PriceRangeFilter;
import com.labanovich.product.dto.ProductCreateUpdateDto;
import com.labanovich.product.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> findAll();

    ProductDto findById(String id);

    List<ProductDto> findBy(PriceRangeFilter priceRangeFilter);

    ProductDto insert(ProductCreateUpdateDto productDto);

    ProductDto update(String id, ProductCreateUpdateDto productDto);

    void delete(String id);
}
