package com.example.geopricing.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "country_pricing_zone")
public class CountryPricingZone {

    @Id
    private String countryCode;

    private String pricingZoneCode;

    public CountryPricingZone() {
    }

    public CountryPricingZone(String countryCode, String pricingZoneCode) {
        this.countryCode = countryCode;
        this.pricingZoneCode = pricingZoneCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPricingZoneCode() {
        return pricingZoneCode;
    }

    public void setPricingZoneCode(String pricingZoneCode) {
        this.pricingZoneCode = pricingZoneCode;
    }
}

