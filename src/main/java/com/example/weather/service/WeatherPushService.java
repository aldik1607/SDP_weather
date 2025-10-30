package com.example.weather.service;

import com.example.weather.model.WeatherData;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WeatherPushService {
    private final SimpMessagingTemplate messagingTemplate;

    public WeatherPushService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void push(WeatherData data) {
        System.out.println("[WeatherPushService] pushing -> " + data);
        messagingTemplate.convertAndSend("/topic/weather", data);
    }
}
