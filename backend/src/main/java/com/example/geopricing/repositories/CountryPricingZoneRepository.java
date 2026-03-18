package com.example.geopricing.repositories;

import com.example.geopricing.entities.CountryPricingZone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryPricingZoneRepository extends JpaRepository<CountryPricingZone, String> {
}

