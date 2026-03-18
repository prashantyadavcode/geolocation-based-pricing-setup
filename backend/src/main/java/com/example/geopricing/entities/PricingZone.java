package com.example.geopricing.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pricing_zone")
public class PricingZone {

    @Id
    private String code;

    private String currency;

    private String symbol;

    public PricingZone() {
    }

    public PricingZone(String code, String currency, String symbol) {
        this.code = code;
        this.currency = currency;
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}

