package com.speriamochemelacavo.turismo2024.models.elements;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "addresses")
public class Address {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonProperty("amenity")
    private String amenity;
    @JsonProperty("house_number")
    private int houseNumber = 0;
    @JsonProperty("road")
    private String road;

    public Address() {

    }

    public Address(String amenity, int houseNumber, String road) {
		this.amenity = amenity;
		this.houseNumber = houseNumber;
		this.road = road;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAmenity() {
        return amenity;
    }

    public void setAmenity(String amenity) {
        this.amenity = amenity;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int house_number) {
        this.houseNumber = house_number;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }
    
    @Override
	public String toString() {
    	String toReturn = 
    			getRoad()
    			+ ", "
    			+ (getHouseNumber() != 0 ? (getHouseNumber()) : "snc");
		return toReturn;
	}
}