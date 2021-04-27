package com.tcode.project.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetailResponse {
    private Long id;
    private String name;
    private int price;
}
