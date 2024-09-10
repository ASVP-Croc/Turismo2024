package com.speriamochemelacavo.turismo2024.services;

import com.speriamochemelacavo.turismo2024.models.elements.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;

@Service
public class POIsService extends ElementsWithContentsService<PointOfInterest> {

	@Autowired
	private AddressService addressService;
	
	public float getLatitudeById(int poiId) {
		return findById(poiId).getLatitude();
	}
	
	public float getLongitudeById(int poiId) {
		return findById(poiId).getLongitude();
	}
	
	public Address getAddressById(int poiId) {
		return findById(poiId).getAddress();
	}
	
	public void setLatitudeById(int poiId, float latitude) {
		findById(poiId).setLatitude(latitude);
	}
	
	public void setLongitudeById(int poiId, float longitude) {
		findById(poiId).setLongitude(longitude);
	}
	
	public void setAddressById(int poiId, Address address) {
		findById(poiId).setAddress(address);
	}
	
	
}

