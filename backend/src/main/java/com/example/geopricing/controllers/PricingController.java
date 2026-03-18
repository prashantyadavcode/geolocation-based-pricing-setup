package com.example.geopricing.controllers;

import com.example.geopricing.services.CountryResolverService;
import com.example.geopricing.services.PricingService;
import com.example.geopricing.services.PricingService.CoursePriceResponse;
import com.example.geopricing.services.PricingService.PricingContextResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class PricingController {

    @Autowired
    private CountryResolverService countryResolverService;

    @Autowired
    private PricingService pricingService;

    @GetMapping("/pricing/context")
    public PricingContextResponse getPricingContext(HttpServletRequest request) {
        CountryResolverService.CountryResolutionResult result = countryResolverService.resolveCountry(request);
        return pricingService.getPricingContext(result.countryCode(), result.source());
    }

    @GetMapping("/courses")
    public List<CoursePriceResponse> getCourses(HttpServletRequest request) {
        CountryResolverService.CountryResolutionResult result = countryResolverService.resolveCountry(request);
        return pricingService.getCoursesForCountry(result.countryCode(), result.source());
    }

    @GetMapping("/courses/{courseId}/price")
    public CoursePriceResponse getCoursePrice(
            @PathVariable Long courseId,
            HttpServletRequest request
    ) {
        CountryResolverService.CountryResolutionResult result = countryResolverService.resolveCountry(request);
        return pricingService.getPriceForCourse(courseId, result.countryCode(), result.source());
    }
}

