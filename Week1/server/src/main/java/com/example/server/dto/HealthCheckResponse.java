package com.example.server.dto;

import lombok.Getter;

@Getter
public class HealthCheckResponse {
    private static final String STATUS = "ok";
    private String status;

    public HealthCheckResponse() {
        this.status = STATUS;
    }
}
