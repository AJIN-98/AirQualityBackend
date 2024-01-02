package com.air.AirQualityBackend.service;

import com.air.AirQualityBackend.model.Country;
import com.air.AirQualityBackend.model.CountryName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class AirServiceImpl implements AirService {

    @Value("${api.country.uri}")
    private String uri;

    @Value("${api.key}")
    private String apiKey;

    // http://api.airvisual.com/v2/countries?key=98ba7871-8e95-4200-93f6-12b22cbaeec1

    @Override
    public ArrayList<String> getCountryList() {
        ArrayList<String> countryList = new ArrayList<>();

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<?> request = new HttpEntity<>(null);
            ResponseEntity<Country> response = restTemplate
                    .exchange(uri + apiKey, HttpMethod.GET, request, Country.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                for (CountryName countryName : response.getBody().data) {
                    countryList.add(countryName.country);
                }
            } else {
                System.err.println("Unexpected response status code: " + response.getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return countryList;
    }
}
