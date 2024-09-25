package com.speriamochemelacavo.turismo2024.services;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.ElementWithContents;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.repository.TagRepository;

@Service
public class TagsService {

	@Autowired
	private TagRepository tagRepository;
	
	public Tag findById(int tagId) throws SQLIntegrityConstraintViolationException{
		Optional<Tag> tagToReturn = tagRepository.findById(tagId);
		if(tagToReturn.isPresent()) {
			return tagToReturn.get();
		} else {

			throw new SQLIntegrityConstraintViolationException("Il Tag con ID " + tagId + " non è stato trovato");
		}
	}
	
	public Tag findByTag(String tagName) throws SQLIntegrityConstraintViolationException{
		Optional<Tag> tagToReturn = tagRepository.findByTagName(tagName);
		if(tagToReturn.isPresent()) {
			return tagToReturn.get();
		} else {
			throw new SQLIntegrityConstraintViolationException("Il Tag " + tagName + " non è stato trovato");
		}
	}
	
	public Tag add(Tag tagToAdd) {
		try {
			Tag toCheck = findByTag(tagToAdd.getTagName());
			tagToAdd.setId(toCheck.getId());
			tagToAdd.getElements().addAll(toCheck.getElements());
		} catch (SQLIntegrityConstraintViolationException e) {
		}
		
		return tagRepository.save(tagToAdd);
	}

	public Set<Tag> addAll(Set<Tag> tagsToAdd) {
		Set<Tag>  toReturn = new HashSet<>();
		tagsToAdd.stream().forEach(t -> toReturn.add(add(t)));
		return toReturn;
	}
	
	public void delete(Tag tagToDelete) {
		tagRepository.delete(tagToDelete);
	}
	
	public <T extends ElementWithContents> Set<Tag> createTagsFromString(String toConvert, T elementWithContentToTag) {
		Set<Tag> toReturn = new HashSet<>();
		split(toConvert).stream().forEach(s -> toReturn.add(new Tag(s, elementWithContentToTag)));
		return toReturn;
	}
	
    public Set<String> split(String toSplit) {
    	Set<String> toReturn = new HashSet<>();
        String tagClean = toSplit.toUpperCase().replaceAll("\\s*,\\s*", ",").trim();
        
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
