package com.example.geopricing.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class CountryResolverService {

    public static final String HEADER_OVERRIDE = "X-Country-Override";
    public static final String HEADER_CLOUDFRONT = "CloudFront-Viewer-Country";
    public static final String DEFAULT_COUNTRY = "US";

    public CountryResolutionResult resolveCountry(HttpServletRequest request) {
        // Country → Zone → Price logic starts with resolving a country code:
        // 1. If X-Country-Override header is present, we use that (simulating a manual selection).
        // 2. Else, if CloudFront-Viewer-Country is present, we use it (simulating AWS CloudFront geolocation).
        // 3. Otherwise we fall back to a default of US.
        String override = headerToCode(request.getHeader(HEADER_OVERRIDE));
        if (override != null && !override.isBlank()) {
            return new CountryResolutionResult(override, ResolutionSource.OVERRIDE.name());
        }

        String cf = headerToCode(request.getHeader(HEADER_CLOUDFRONT));
        if (cf != null && !cf.isBlank()) {
            return new CountryResolutionResult(cf, ResolutionSource.CLOUDFRONT.name());
        }

        return new CountryResolutionResult(DEFAULT_COUNTRY, ResolutionSource.DEFAULT.name());
    }

    private String headerToCode(String raw) {
        return raw == null ? null : raw.trim().toUpperCase();
    }

    public enum ResolutionSource {
        OVERRIDE,
        CLOUDFRONT,
        DEFAULT
    }

    public record CountryResolutionResult(String countryCode, String source) {
    }
}

