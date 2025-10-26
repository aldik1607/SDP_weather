package com.example.weather;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Create strategies
        RealTimeSensorStrategy realTime = new RealTimeSensorStrategy(22.0, 50.0, 1013.0);
        ScheduledBatchStrategy batch = new ScheduledBatchStrategy();
        ManualInputStrategy manual = new ManualInputStrategy();


        // Create station with initial strategy
        WeatherStation station = new WeatherStation(realTime);


        // Create observers
        PhoneDisplay phone = new PhoneDisplay("AldiyarPhone");
        AppDisplay app = new AppDisplay("WeatherPro");
        ConsoleDisplay console = new ConsoleDisplay();


        station.registerObserver(phone);
        station.registerObserver(app);
        station.registerObserver(console);


        // Demonstrate a few real-time updates
        station.updateNow();
        Thread.sleep(500);
        station.updateNow();


        // Switch to batch strategy
        station.setStrategy(batch);
        station.updateNow();


        // Switch to manual and provide data
        station.setStrategy(manual);
        manual.setManualData(new WeatherData(-5.0, 80.0, 1005.0));
        station.updateNow();


        // Unregister one observer and update again
        station.unregisterObserver(app);
        manual.setManualData(new WeatherData(0.0, 70.0, 1008.0));
        station.updateNow();


        System.out.println("Demo finished.");
    }
}