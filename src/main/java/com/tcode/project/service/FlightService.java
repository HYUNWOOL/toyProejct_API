package com.tcode.project.service;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.Location;
import com.tcode.project.ErrorMessage;
import com.tcode.project.model.response.AirportListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Service
@Slf4j
public class FlightService {

  @Value("${amadeus.apiKey}")
  private String apiKey;

  @Value("${amadeus.apiSecret}")
  private String apiSecret;

  public List<AirportListResponse> getAirports(String code) throws ResponseException {
    List<AirportListResponse> result = new ArrayList<>();
    Amadeus amadeus = Amadeus.builder(apiKey, apiSecret).build();
    code = code.toUpperCase(Locale.ROOT);
    Location[] locations =
        amadeus.referenceData.locations.get(
            Params.with("keyword", code).and("subType", Locations.AIRPORT));

    if (locations[0].getResponse().getStatusCode() != 200) {
      log.error("Wrong status code: " + locations[0].getResponse().getStatusCode());
      throw new RuntimeException(ErrorMessage.FLIGHT_SEARCH_UNKNOWN_ERROR);
    }

    for (Location location : locations) {
      result.add(
          AirportListResponse.builder()
              .name(location.getName())
              .detailName(location.getDetailedName())
              .address(location.getAddress())
              .analytics(location.getAnalytics())
              .iataCode(location.getIataCode())
              .latitude(location.getGeoCode().getLatitude())
              .longitude(location.getGeoCode().getLongitude())
              .build());
    }
    return result;
  }
}
