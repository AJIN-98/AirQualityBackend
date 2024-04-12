package com.air.AirQualityBackend.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Configuration
@ConfigurationProperties(prefix = "api")
public class ApiProperties {
    @NestedConfigurationProperty
    private final Uri uri = new Uri();

    @Data
    public static class Uri {
        private String country;
        private String state;
        private String key;
    }
}
