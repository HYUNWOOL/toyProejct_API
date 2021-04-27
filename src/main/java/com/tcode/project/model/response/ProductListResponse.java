package com.tcode.project.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ProductListResponse {
    private Long id;
    private String name;
    private int price;
}
