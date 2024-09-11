package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class NominatimResponse {

	@JsonProperty("place_id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("type")
	private String description;
    @JsonProperty("lat")
    private float lat;
    @JsonProperty("lon")
    private float lon;
    @JsonProperty("address")
    private NominatimAddress address;
    
    public NominatimResponse() {
    	
    }
    
    @Component
    public static class NominatimAddress {
    	@JsonProperty("emergency")
        private String emergency;
    	@JsonProperty("historic")
        private String historic;
    	@JsonProperty("military")
        private String military;
    	@JsonProperty("natural")
        private String natural;
    	@JsonProperty("landuse")
        private String landuse;
    	@JsonProperty("place")
        private String place;
    	@JsonProperty("railway")
        private String railway;
    	@JsonProperty("man_made")
        private String manMade;
    	@JsonProperty("aerialway")
        private String aerialway;
    	@JsonProperty("boundary")
        private String boundary;
    	@JsonProperty("amenity")
        private String amenity;
    	@JsonProperty("aeroway")
        private String aeroway;
    	@JsonProperty("club")
        private String club;
    	@JsonProperty("craft")
        private String craft;
    	@JsonProperty("leisure")
        private String leisure;
    	@JsonProperty("office")
        private String office;
    	@JsonProperty("mountain_pass")
        private String mountainPass;
    	@JsonProperty("shop")
        private String shop;
    	@JsonProperty("tourism")
        private String tourism;
    	@JsonProperty("bridge")
        private String bridge;
    	@JsonProperty("tunnel")
        private String tunnel;
    	@JsonProperty("waterway")
        private String waterway;
        @JsonProperty("house_number")
        private String houseNumber;
        @JsonProperty("house_name")
        private String houseName;
        @JsonProperty("neighbourhood")
        private String neighbourhood;
        @JsonProperty("allotments")
        private String allotments;
        @JsonProperty("quarter")
        private String quarter;
        @JsonProperty("municipality")
        private String municipality;
        @JsonProperty("city")
        private String city;
        @JsonProperty("town")
        private String town;
        @JsonProperty("village")
        private String village;
        @JsonProperty("road")
        private String road;
        @JsonProperty("postcode")
        private String postcode;
        
        public NominatimAddress() {
        	
        }
        
        public String getAmenity() {
        	String toReturn = emergency + historic + military + natural + landuse + place
        			 + railway + manMade + aerialway + boundary + amenity + aeroway + club
        			 + craft + leisure + office + mountainPass + shop + tourism + bridge + tunnel + waterway;
        	return toReturn;
        }
        
        public String getHouseNumber() {
        	String toReturn = houseNumber + houseName;
        	if (toReturn == "") {
        		return "snc";
        	}
        	return toReturn;
        }
        
        public String getQuarter() {
        	String toReturn = neighbourhood + allotments + quarter;
        	return toReturn;
        }
        
        public String getCity() {
        	String toReturn = municipality + city + town + village;
        	return toReturn;
        }

        public String getRoad() {
			return road;
		}

        public String getPostcode() {
			return postcode;
		}
        
    }
    
    public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public float getLat() {
		return lat;
	}

	public float getLon() {
		return lon;
	}

	public String getAmenity() {
    	return address.getAmenity();
    }
    
    public String getHouseNumber() {
    	return address.getHouseNumber();
    }
    
    public String getQuarter() {
    	return address.getQuarter();
    }
    
    public String getCity() {
    	return address.getCity();
    }
    
	public String getRoad() {
		return address.getRoad();
	}

	public String getPostcode() {
		return address.getPostcode();
	}
}
