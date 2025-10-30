package com.example.weather.external;

import com.example.weather.model.ExternalResponse;

public class DummyExternalClient {
    public ExternalResponse fetch() {
        // in real case perform HTTP call; here random/demo values
        return new ExternalResponse(15.2, 60.0, 1010.0);
    }
}