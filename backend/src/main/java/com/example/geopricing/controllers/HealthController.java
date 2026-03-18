package com.example.geopricing.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/")
    public Map<String, String> root() {
        return Map.of(
                "status", "ok",
                "hint", "Backend is running. Try /api/courses or /api/pricing/context"
        );
    }

    @GetMapping("/healthz")
    public Map<String, String> healthz() {
        return Map.of("status", "ok");
    }
}

