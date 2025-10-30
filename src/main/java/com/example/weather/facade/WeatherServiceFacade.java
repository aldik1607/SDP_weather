package com.example.weather.facade;

import com.example.weather.core.WeatherStation;
import com.example.weather.factory.StrategyFactory;
import com.example.weather.model.WeatherData;
import com.example.weather.strategy.ManualInputStrategy;
import com.example.weather.strategy.UpdateStrategy;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceFacade {
    private final WeatherStation station;
    private final ManualInputStrategy manual;

    public WeatherServiceFacade(WeatherStation station, ManualInputStrategy manual) {
        this.station = station;
        this.manual = manual;
    }

    @PostConstruct
    public void init() {
        System.out.println("[WeatherServiceFacade] initialized (initial update deferred).");
    }


    public WeatherData getCurrent() {
        return station.getLastData();
    }

    public void switchStrategy(String type) {
        if ("manual".equalsIgnoreCase(type)) {
            station.setStrategy(manual);
            System.out.println("[WeatherServiceFacade] Switched to ManualInputStrategy — waiting for manual data (POST /api/weather/manual).");
            return;
        }

        UpdateStrategy s = StrategyFactory.create(type);
        station.setStrategy(s);
        station.updateNow();
    }


    public void switchStrategy(String type, WeatherData initialData) {
        if ("manual".equalsIgnoreCase(type)) {
            manual.setManualData(initialData);
            station.setStrategy(manual);
            station.updateNow();
            return;
        }

        UpdateStrategy s = StrategyFactory.create(type);
        station.setStrategy(s);
        station.updateNow();
    }

    public void manualUpdate(WeatherData data) {
        manual.setManualData(data);
        station.setStrategy(manual);
        station.updateNow();
    }

    public void forceUpdateNow() {
        station.updateNow();
    }

}
