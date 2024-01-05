package com.air.AirQualityBackend.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface AirService {
    ArrayList<String> getCounties();

    ArrayList<String> getStates(String country);
}
