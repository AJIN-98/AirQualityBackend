package com.air.AirQualityBackend.controller;

import com.air.AirQualityBackend.service.AirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/country")
public class AirApiController {

    @Autowired
    AirService airService;

    @GetMapping("/list")
    public ResponseEntity<ArrayList<String>> getAllCity() {
        try {
            ArrayList<String> countryList = airService.getCountryList();
            return ResponseEntity.ok(countryList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
