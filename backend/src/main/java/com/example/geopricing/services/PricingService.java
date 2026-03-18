package com.example.geopricing.services;

import com.example.geopricing.entities.CountryPricingZone;
import com.example.geopricing.entities.Course;
import com.example.geopricing.entities.CoursePrice;
import com.example.geopricing.entities.PricingZone;
import com.example.geopricing.repositories.CountryPricingZoneRepository;
import com.example.geopricing.repositories.CoursePriceRepository;
import com.example.geopricing.repositories.CourseRepository;
import com.example.geopricing.repositories.PricingZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Service
public class PricingService {

    @Autowired
    private CountryPricingZoneRepository countryPricingZoneRepository;

    @Autowired
    private PricingZoneRepository pricingZoneRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CoursePriceRepository coursePriceRepository;

    // Country → Zone → Price logic:
    // - We first convert a country code (IN, US, DE, etc.) into a pricing zone
    //   (INDIA, GLOBAL_USD, EUROPE).
    // - Each pricing zone has a currency and symbol.
    // - For every (course, pricing zone) pair there is a price.
    //
    // This service encapsulates that mapping and formats the final price that
    // the API returns to the frontend.

    public String resolvePricingZoneCode(String countryCode) {
        if (countryCode == null) {
            return "GLOBAL_USD";
        }
        Optional<CountryPricingZone> mappingOpt = countryPricingZoneRepository.findById(countryCode);
        return mappingOpt.map(CountryPricingZone::getPricingZoneCode).orElse("GLOBAL_USD");
    }

    public PricingContextResponse getPricingContext(String countryCode, String source) {
        String zoneCode = resolvePricingZoneCode(countryCode);
        PricingZone zone = pricingZoneRepository.findById(zoneCode)
                .orElseThrow(() -> new IllegalStateException("Missing pricing zone: " + zoneCode));
        return new PricingContextResponse(countryCode, zoneCode, source);
    }

    public List<CoursePriceResponse> getCoursesForCountry(String countryCode, String source) {
        String zoneCode = resolvePricingZoneCode(countryCode);
        PricingZone zone = pricingZoneRepository.findById(zoneCode)
                .orElseThrow(() -> new IllegalStateException("Missing pricing zone: " + zoneCode));

        List<Course> courses = courseRepository.findAll();

        return courses.stream()
                .map(course -> buildCoursePriceResponse(course, zone, countryCode, source))
                .toList();
    }

    public CoursePriceResponse getPriceForCourse(Long courseId, String countryCode, String source) {
        String zoneCode = resolvePricingZoneCode(countryCode);
        PricingZone zone = pricingZoneRepository.findById(zoneCode)
                .orElseThrow(() -> new IllegalStateException("Missing pricing zone: " + zoneCode));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found: " + courseId));

        return buildCoursePriceResponse(course, zone, countryCode, source);
    }

    private CoursePriceResponse buildCoursePriceResponse(
            Course course,
            PricingZone zone,
            String countryCode,
            String source
    ) {
        Optional<CoursePrice> priceOpt =
                coursePriceRepository.findByCourseIdAndPricingZoneCode(course.getId(), zone.getCode());

        Integer amount = priceOpt.map(CoursePrice::getAmount).orElse(0);
        String displayAmount = formatAmount(zone.getSymbol(), amount);

        return new CoursePriceResponse(
                course.getId(),
                course.getTitle(),
                countryCode,
                zone.getCode(),
                zone.getCurrency(),
                zone.getSymbol(),
                amount,
                displayAmount,
                source
        );
    }

    private String formatAmount(String symbol, Integer amount) {
        DecimalFormat df = new DecimalFormat("#,###");
        String formatted = df.format(amount);
        return symbol + formatted;
    }

    public record PricingContextResponse(
            String countryCode,
            String pricingZone,
            String source
    ) {
    }

    public record CoursePriceResponse(
            Long courseId,
            String title,
            String countryCode,
            String pricingZone,
            String currency,
            String symbol,
            Integer amount,
            String display,
            String source
    ) {
    }
}

