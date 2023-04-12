package com.labanovich.product.service.impl;

import com.labanovich.product.dto.PriceRangeFilter;
import com.labanovich.product.dto.ProductCreateUpdateDto;
import com.labanovich.product.dto.ProductDto;
import com.labanovich.product.entity.Product;
import com.labanovich.product.exception.EntityNotFoundException;
import com.labanovich.product.mapper.ProductMapper;
import com.labanovich.product.repostitory.ProductRepository;
import com.labanovich.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    //          -> userService
    //  req ->     productService
    //          -> orderService
    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductDto)
                .toList();
    }

    @Override
    public ProductDto findById(String id) {
        return productRepository.findById(id)
                .map(productMapper::toProductDto)
                .orElseThrow(() -> new EntityNotFoundException("id", Product.class, id));
    }

    @Override
    public List<ProductDto> findBy(PriceRangeFilter priceRangeFilter) {
        return productRepository.findAllByPriceBetween(priceRangeFilter.minPrice(), priceRangeFilter.maxPrice())
                .stream()
                .map(productMapper::toProductDto)
                .toList();
    }

    @Override
    public ProductDto insert(ProductCreateUpdateDto productDto) {
        Product product = productMapper.createDtoToProduct(productDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.toProductDto(savedProduct);
    }

    @Override
    public ProductDto update(String id, ProductCreateUpdateDto updateDto) {
        return productRepository.findById(id)
                .map(product -> productMapper.updateDtoToProduct(updateDto, product.getId()))
                .map(productRepository::save)
                .map(productMapper::toProductDto)
                .orElseThrow(() -> new EntityNotFoundException("id", Product.class, id));
    }

    @Override
    public void delete(String id) {
        productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return product;
                })
                .orElseThrow(() -> new EntityNotFoundException("id", Product.class, id));
    }
}
