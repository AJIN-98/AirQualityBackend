package com.air.AirQualityBackend.service;

import com.air.AirQualityBackend.config.ApiProperties;
import com.air.AirQualityBackend.model.Country;
import com.air.AirQualityBackend.model.CountryName;
import com.air.AirQualityBackend.model.State;
import com.air.AirQualityBackend.model.StateName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Logger;

@Slf4j
@Service
public class AirServiceImpl implements AirService {

    @Autowired
    ApiProperties apiProperties;


    public AirServiceImpl(ApiProperties apiProperties) {
        this.apiProperties = apiProperties;


    }

    @Override
    public ArrayList<String> getCounties() {
        ArrayList<String> countryList = new ArrayList<>();

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<?> request = new HttpEntity<>(null);
            log.info(apiProperties.getUri().getCountry());
            log.info(apiProperties.getUri().getKey());
            ResponseEntity<Country> response = restTemplate.exchange(apiProperties.getUri().getCountry() + "?key=" + apiProperties.getUri().getKey(), HttpMethod.GET, request, Country.class);

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
            ResponseEntity<State> response = restTemplate.exchange(apiProperties.getUri().getState() + country + "&key=" + apiProperties.getUri().getKey(), HttpMethod.GET, request, State.class);
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
