package com.speriamochemelacavo.turismo2024.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.ElementWithContents;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.repository.TagRepository;

@Service
public class TagsService {

	@Autowired
	private TagRepository tagRepository;
	
	public Tag findById(int id) {
		Optional<Tag> tagToReturn = tagRepository.findById(id);
		if(tagRepository.findById(id).isPresent()) {;
			return tagToReturn.get();
		} else throw new NoSuchElementException("Il Tag non è stato trovato");
	}
	
	public Tag findByTag(String tag) {
		Optional<Tag> tagToReturn = tagRepository.findByTag(tag);
		if(tagRepository.findByTag(tag).isPresent()) {
			return tagToReturn.get();
		} else throw new NoSuchElementException("Il Tag " + tag + " non è stato trovato");
	}
	
	public void add(Tag tagToAdd) {
//		TODO togliere il try una volta completato il progetto
		tagRepository.save(tagToAdd);
	}
	
	public void add(Tag tagToAdd, ElementWithContents element) {
		tagToAdd.getElements().add(element);
		add(tagToAdd);
	}
	
	public void addAll(List<Tag> tagsToAdd) {
		tagsToAdd.stream().forEach(t -> add(t));
	}
	
	public void addAll(List<Tag> tagsToAdd, ElementWithContents element) {
		tagsToAdd.stream().forEach(t -> add(t, element));
	}
	
	public void delete(Tag tagToDelete) {
		tagRepository.delete(tagToDelete);
	}
	
	public Set<Tag> createTagsFromString(String toConvert, ElementWithContents element) {
		Set<Tag> toReturn = new HashSet<>();
		split(toConvert).stream().forEach(s -> toReturn.add(new Tag(s, element)));
		return toReturn;
	}
	
    public Set<String> split(String toSplit) {
    	Set<String> toReturn = new HashSet<>();
        String tagClean = toSplit.toUpperCase().replaceAll("\\s*,\\s*", ",").trim();
        String tagClean2 = tagClean.replaceAll("\\s*", "-");
        
        Set<String> firstSplit = Arrays.stream(tagClean.split(","))
        		.filter(t -> !t.isEmpty())
        		.collect(Collectors.toSet());
        toReturn.addAll(firstSplit);
        toReturn.addAll(secondSplit(firstSplit));
        return toReturn;
    }
    
    private Set<String> secondSplit(Set<String> toSplit) {
    	Set<String> toReturn = new HashSet<>();
    	
    	toSplit.stream()
    			.forEach(s -> {
    				Arrays.stream(s.split("\\s+")).forEach(s2 -> toReturn.add(s2));
    				});
    	
        return toReturn;
    }
}
