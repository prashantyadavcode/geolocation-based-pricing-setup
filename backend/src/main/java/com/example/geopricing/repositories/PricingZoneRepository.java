package com.example.geopricing.repositories;

import com.example.geopricing.entities.PricingZone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingZoneRepository extends JpaRepository<PricingZone, String> {
}

