package com.labanovich.product.controller;

import com.labanovich.product.dto.PriceRangeFilter;
import com.labanovich.product.dto.ProductCreateUpdateDto;
import com.labanovich.product.dto.ProductDto;
import com.labanovich.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findBy(@PathVariable String id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<ProductDto>> findBy(PriceRangeFilter priceRangeFilter) {
        return ResponseEntity.ok(productService.findBy(priceRangeFilter));
    }

    @PostMapping
    public ResponseEntity<ProductDto> save(@RequestBody ProductCreateUpdateDto createUpdateDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.insert(createUpdateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable String id, @RequestBody ProductCreateUpdateDto createUpdateDto) {
        return ResponseEntity.ok()
                .body(productService.update(id, createUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.noContent()
            .build();
    }
}
