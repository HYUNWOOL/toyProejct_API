package com.tcode.project.service;

import com.tcode.project.domain.entity.Product;
import com.tcode.project.model.response.ProductDetailResponse;
import com.tcode.project.model.response.ProductListResponse;
import com.tcode.project.repository.ProductJpaRepository;
import com.tcode.project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductJpaRepository productJpaRepository;

    public List<ProductListResponse> getProducts(){
        List<ProductListResponse> result = new ArrayList<>();

        List<Product> products = productJpaRepository.findAll();
        for(Product product : products){
            result.add(ProductListResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build()
            );
        }
        return result;
    }

    public ProductDetailResponse getProductDetail(long id){
        Product product = productJpaRepository.getOne(id);
    return ProductDetailResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .price(product.getPrice())
        .build();
    }
}
