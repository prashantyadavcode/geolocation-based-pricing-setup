package com.example.geopricing.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "course_price")
public class CoursePrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long courseId;

    private String pricingZoneCode;

    private Integer amount;

    public CoursePrice() {
    }

    public CoursePrice(Long id, Long courseId, String pricingZoneCode, Integer amount) {
        this.id = id;
        this.courseId = courseId;
        this.pricingZoneCode = pricingZoneCode;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getPricingZoneCode() {
        return pricingZoneCode;
    }

    public void setPricingZoneCode(String pricingZoneCode) {
        this.pricingZoneCode = pricingZoneCode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

