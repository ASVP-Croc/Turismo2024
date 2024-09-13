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
		this.addressRepository.save(address);
	}
	
	public void update(Address addressToUpdate) {
		addressRepository.save(addressToUpdate);
	}
	
	public void delete(Address addressToDelete) {
		addressRepository.delete(addressToDelete);
	}
	
	public void deleteAll(List<Address> addressToDelete) {
		addressRepository.deleteAll(addressToDelete);
	}
}
