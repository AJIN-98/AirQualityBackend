package com.air.AirQualityBackend.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Country {
    public String status;
    public ArrayList<CountryName> data;

}
