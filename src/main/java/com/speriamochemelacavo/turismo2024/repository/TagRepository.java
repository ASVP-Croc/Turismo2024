package com.speriamochemelacavo.turismo2024.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.speriamochemelacavo.turismo2024.models.elements.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer>{
	
	Optional<Tag> findByTagName(String tag);
}
