package com.speriamochemelacavo.turismo2024.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.ElementStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface ElementRepository<T extends Element> extends JpaRepository<T, Integer>{
	
    List<T> findAllByValidated(ElementStatus elementStatus);
}


