package com.tcode.project.controller;

import com.amadeus.exceptions.ResponseException;
import com.tcode.project.Constant;
import com.tcode.project.model.response.AirportListResponse;
import com.tcode.project.service.FlightService;
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
public class TFlightController {

  private final FlightService service;

  // 공항검색 api
  @GetMapping(Constant.API + "/airports")
  public ResponseEntity<List<AirportListResponse>> getFlights(String code) throws ResponseException {
    return new ResponseEntity<>(service.getAirports(code), HttpStatus.OK);
  }
}
