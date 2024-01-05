package com.air.AirQualityBackend.controller;

import com.air.AirQualityBackend.model.Country;
import com.air.AirQualityBackend.model.CountryName;
import com.air.AirQualityBackend.service.AirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/air")
public class AirApiController {

    @Autowired
    AirService airService;

    @GetMapping("/countries")
    public ResponseEntity<?> getAllCountries() {
        try {
            return ResponseEntity.ok(airService.getCounties());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/states")
    public ResponseEntity<?> getAllStates(@RequestBody CountryName country) {
        try {
            return ResponseEntity.ok(airService.getStates(country.getCountry()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
