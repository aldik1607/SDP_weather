package com.example.weather.observer;

import com.example.weather.service.WeatherPushService;
import com.example.weather.model.WeatherData;
import org.springframework.stereotype.Component;

@Component
public class WebSocketObserver implements Observer {
    private final WeatherPushService pushService;

    public WebSocketObserver(WeatherPushService pushService) {
        this.pushService = pushService;
    }

    @Override
    public void update(WeatherData data) {
        pushService.push(data);
    }
}
