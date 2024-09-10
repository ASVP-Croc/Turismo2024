package com.speriamochemelacavo.turismo2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.speriamochemelacavo.turismo2024.models.elements.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
