package com.tcode.project.controller;

import com.tcode.project.Constant;
import com.tcode.project.model.response.ProductDetailResponse;
import com.tcode.project.model.response.ProductListResponse;
import com.tcode.project.model.response.StockListResponse;
import com.tcode.project.service.ProductService;
import com.tcode.project.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tcode")
public class TStockController {

    private final StockService stockService;

    //주식 api
    @GetMapping(Constant.API + "/stocks")
    public ResponseEntity<List<StockListResponse>> getProducts(String keyword) throws IOException, ParseException {

        return new ResponseEntity<>(stockService.getStock(keyword), HttpStatus.OK);
    }
}
