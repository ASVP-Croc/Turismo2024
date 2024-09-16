package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Address;
import com.speriamochemelacavo.turismo2024.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Address findById(int addressToFindId) {
		return addressRepository.findById(addressToFindId).orElseThrow();
	}
	
	public List<Address> findAll(){
		return addressRepository.findAll();
	}
	
	public void add(Address address) {
		Address optionalAddress;
		try {
			optionalAddress = findById(address.getId());
			address.setId(optionalAddress.getId());
		} catch (Exception e) {
		}
		addressRepository.save(address);
	}
	
	public void delete(Address addressToDelete) {
		addressRepository.delete(addressToDelete);
	}
	
	public void deleteAll(List<Address> addressToDelete) {
		addressRepository.deleteAll(addressToDelete);
	}
}
