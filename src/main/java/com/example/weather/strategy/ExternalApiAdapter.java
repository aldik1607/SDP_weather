package com.example.weather.strategy;

import com.example.weather.external.DummyExternalClient;
import com.example.weather.model.ExternalResponse;
import com.example.weather.model.WeatherData;

public class ExternalApiAdapter implements UpdateStrategy {
    private final DummyExternalClient client;

    public ExternalApiAdapter(DummyExternalClient client) {
        this.client = client;
    }

    @Override
    public WeatherData fetch() {
        ExternalResponse r = client.fetch();
        // создаём WeatherData напрямую через конструктор
        return new WeatherData(r.tempC, r.hum, r.pres);
    }
}
