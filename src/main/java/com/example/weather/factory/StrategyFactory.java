package com.example.weather.factory;

import com.example.weather.external.DummyExternalClient;
import com.example.weather.strategy.ExternalApiAdapter;
import com.example.weather.strategy.RealTimeSensorStrategy;
import com.example.weather.strategy.ScheduledBatchStrategy;
import com.example.weather.strategy.UpdateStrategy;

public class StrategyFactory {
    public static UpdateStrategy create(String type) {
        switch ((type==null) ? "" : type.toLowerCase()) {
            case "realtime":
            case "real":
            case "sensor":
                return new RealTimeSensorStrategy(22.0, 50.0, 1013.0);
            case "batch":
            case "scheduled":
                return new ScheduledBatchStrategy();
            case "manual":
                throw new IllegalArgumentException("Do not create ManualInputStrategy via StrategyFactory. Use the injected bean.");
            case "external":
                return new ExternalApiAdapter(new DummyExternalClient());
            default:
                throw new IllegalArgumentException("Unknown strategy: " + type);
        }
    }
}
