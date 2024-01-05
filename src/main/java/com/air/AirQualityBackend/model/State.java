package com.air.AirQualityBackend.model;

import lombok.Data;

import java.util.ArrayList;
@Data
public class State {
    private String status;
    private ArrayList<StateName> data;
}
