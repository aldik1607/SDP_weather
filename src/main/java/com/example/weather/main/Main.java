package com.example.weather.main;

import com.example.weather.core.WeatherStation;
import com.example.weather.decorator.LoggingObserver;
import com.example.weather.decorator.ThrottlingObserver;
import com.example.weather.model.WeatherData;
import com.example.weather.observer.AppDisplay;
import com.example.weather.observer.ConsoleDisplay;
import com.example.weather.observer.PhoneDisplay;
import com.example.weather.observer.WebSocketObserver;
import com.example.weather.strategy.ManualInputStrategy;
import com.example.weather.strategy.ScheduledBatchStrategy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Main implements CommandLineRunner {

    private final WeatherStation station;
    private final WebSocketObserver webSocketObserver;
    private final ManualInputStrategy manual;

    public Main(WeatherStation station,
                WebSocketObserver webSocketObserver,
                ManualInputStrategy manual) {
        this.station = station;
        this.webSocketObserver = webSocketObserver;
        this.manual = manual;
    }

    @Override
    public void run(String... args) throws Exception {
        station.registerObserver(webSocketObserver);

        Thread.sleep(1500);

        PhoneDisplay phone = new PhoneDisplay("AldiyarPhone");
        AppDisplay app = new AppDisplay("WeatherPro");
        ConsoleDisplay console = new ConsoleDisplay();

        station.registerObserver(new ThrottlingObserver(new LoggingObserver(phone), 1000));
        station.registerObserver(new ThrottlingObserver(new LoggingObserver(app), 2000));
        station.registerObserver(new LoggingObserver(console));

        station.updateNow();

        Thread.sleep(500);
        station.updateNow();


        station.setStrategy(new ScheduledBatchStrategy());
        station.updateNow();

        station.setStrategy(manual);
        manual.setManualData(new WeatherData(-5.0, 80.0, 1005.0));
        station.updateNow();


        manual.setManualData(new WeatherData(0.0, 70.0, 1008.0));
        station.updateNow();

        System.out.println("Demo finished.");
    }
}
