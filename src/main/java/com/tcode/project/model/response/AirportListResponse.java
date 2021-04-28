package com.tcode.project.model.response;

import com.amadeus.resources.Location;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportListResponse {
    private Location.Address address;
    private String detailName;
    private String name;
    private String iataCode;
    private Location.Analytics analytics;
    private double latitude;
    private double longitude;
}
