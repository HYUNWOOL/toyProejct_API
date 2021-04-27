package com.tcode.project.controller;

import com.tcode.project.Constant;
import com.tcode.project.model.response.ProductDetailResponse;
import com.tcode.project.model.response.ProductListResponse;
import com.tcode.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tcode")
public class TcodeController {

    private final ProductService service;

    //상품 리스트 api
    @GetMapping(Constant.API + "/products")
    public ResponseEntity<List<ProductListResponse>> getProducts() {
        List<ProductListResponse> response = service.getProducts();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //상품 상세 api
    @GetMapping(Constant.API + "/products/{productId}")
    public ResponseEntity<ProductDetailResponse> getProductDetail() {
        ProductDetailResponse response = service.getProductDetail();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
