package com.speriamochemelacavo.turismo2024.repository;

import com.speriamochemelacavo.turismo2024.models.elements.MultimediaMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultimediaMaterialRepository extends JpaRepository<MultimediaMaterial, Integer> {
}
