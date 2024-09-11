package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.repository.TagRepository;

@Service
public class TagsService {

	@Autowired
	private TagRepository tagRepository;
	
	public Tag findById(int id) {
		return tagRepository.findById(id).orElseThrow();
	}
	
	public Tag findByTag(String tag) {
		return tagRepository.findByTag(tag);
	}
	
	public void add(Tag tagToAdd) {
		tagRepository.save(tagToAdd);
	}
	
	public void addAll(List<Tag> tagsToAdd) {
		tagsToAdd.stream().forEach(t -> tagRepository.save(t));
	}
	
	public void update(Tag tagToUpdate) {
		tagRepository.save(tagToUpdate);
	}
	
	public void delete(Tag tagToDelete) {
		tagRepository.delete(tagToDelete);
	}
	
}
