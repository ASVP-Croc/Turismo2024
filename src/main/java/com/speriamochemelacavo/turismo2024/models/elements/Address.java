package com.speriamochemelacavo.turismo2024.models.elements;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "addresses", indexes = {
	    @Index(name = "idx_road", columnList = "road", unique = false)})
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonProperty("amenity")
    private String amenity;
    @JsonProperty("house_number")
    private String house_number;
    @JsonProperty("road")
    private String road;
    @JsonProperty("quarter")
    private String quarter;

    public Address() {

    }

    public Address(Integer id, String amenity, String house_number, String road, String quarter) {
		this.id = id;
		this.amenity = amenity;
		this.house_number = house_number;
		this.road = road;
		this.quarter = quarter;
	}

	public String getAmenity() {
        return amenity;
    }

    public void setAmenity(String amenity) {
        this.amenity = amenity;
    }

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }
}