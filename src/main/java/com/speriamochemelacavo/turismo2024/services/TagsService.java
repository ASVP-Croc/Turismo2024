package com.speriamochemelacavo.turismo2024.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.repository.TagRepository;

@Service
public class TagsService {

	@Autowired
	private TagRepository tagRepository;
	
	public void addTag(Tag tag) {
		tagRepository.save(tag);
	}
	
	public Tag findById(int id) {
		return tagRepository.findById(id).orElseThrow();
	}
	
	public Tag findByTag(String tag) {
		return tagRepository.findByTag(tag);
	}
}
