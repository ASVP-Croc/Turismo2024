package com.speriamochemelacavo.turismo2024.repository;

import org.springframework.stereotype.Repository;

import com.speriamochemelacavo.turismo2024.models.elements.ElementWithContents;

@Repository
public interface ElementWithContentsRepository<T extends ElementWithContents> extends ElementRepository<T>{
	
}


