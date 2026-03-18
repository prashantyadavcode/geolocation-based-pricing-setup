package com.example.geopricing.repositories;

import com.example.geopricing.entities.CoursePrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoursePriceRepository extends JpaRepository<CoursePrice, Long> {

    Optional<CoursePrice> findByCourseIdAndPricingZoneCode(Long courseId, String pricingZoneCode);
}

