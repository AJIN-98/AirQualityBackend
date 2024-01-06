package com.air.AirQualityBackend.service;

import com.air.AirQualityBackend.model.Country;
import com.air.AirQualityBackend.model.CountryName;
import com.air.AirQualityBackend.model.State;
import com.air.AirQualityBackend.model.StateName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class AirServiceImpl implements AirService {

    @Value("${api.country.uri}")
    private String countryUrl;
    @Value("${api.state.uri}")
    private String stateUrl;

    @Value("${api.key}")
    private String apiKey;

    @Override
    public ArrayList<String> getCounties() {
        ArrayList<String> countryList = new ArrayList<>();

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<?> request = new HttpEntity<>(null);
            ResponseEntity<Country> response = restTemplate.exchange(countryUrl + "?key=" + apiKey, HttpMethod.GET, request, Country.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                for (CountryName countryName : Objects.requireNonNull(response.getBody()).getData()) {
                    countryList.add(countryName.getCountry());
                }
            } else {
                System.err.println("Unexpected response status code: " + response.getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return countryList;
    }

    @Override
    public ArrayList<String> getStates(String country) {
        ArrayList<String> stateList = new ArrayList<>();

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<?> request = new HttpEntity<>(country);
            ResponseEntity<State> response = restTemplate.exchange(stateUrl + country + "&key=" + apiKey, HttpMethod.GET, request, State.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                for (StateName stateName : response.getBody().getData()) {
                    stateList.add(stateName.getState());
                }
            } else {
                System.err.println("Unexpected response status code: " + response.getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stateList;
    }
}
