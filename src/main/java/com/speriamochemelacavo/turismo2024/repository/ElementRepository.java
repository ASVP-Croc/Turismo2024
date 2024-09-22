package com.speriamochemelacavo.turismo2024.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.speriamochemelacavo.turismo2024.models.elements.Element;

import java.util.List;

@Repository
public interface ElementRepository<T extends Element> extends JpaRepository<T, Integer>{
    List<T> findAllByValidated(String elementStatus);
}


