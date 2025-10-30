package com.example.weather.config;

import com.example.weather.strategy.ManualInputStrategy;
import com.example.weather.factory.StrategyFactory;
import com.example.weather.core.WeatherStation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherConfig {

    @Bean
    public WeatherStation weatherStation() {
        return new WeatherStation(StrategyFactory.create("realtime"));
    }

    @Bean
    public ManualInputStrategy manualInputStrategy() {
        return new ManualInputStrategy();
    }
}
