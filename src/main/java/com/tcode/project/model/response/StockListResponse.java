package com.tcode.project.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockListResponse {
    private String stockDate;
    private String openPrice;
    private String closePrice;
    private String highPrice;
    private String lowPrice;
    private String volumeData;
}
