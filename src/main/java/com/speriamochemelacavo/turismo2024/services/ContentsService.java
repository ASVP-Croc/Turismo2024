package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.ElementWithContents;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.models.users.User;

@Service
public class ContentsService extends ElementsService<Content>{

	@Override
	public void add(Content contentToAdd, User author) {
		super.add(contentToAdd, author);
	}

	@Override
	public void add(Content contentToAdd, User author, List<Tag> tags) {
		super.add(contentToAdd, author, tags);
	}
	
	public void add(Content contentToAdd, User author, List<Tag> tags, ElementWithContents referenced, String pathToResource) {
		contentToAdd.setReferenced(referenced);
		contentToAdd.setResource(pathToResource);
		super.add(contentToAdd, author, tags);
	}

	@Override
	public void deleteById(Integer id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Content contentToDelete) {
		super.delete(contentToDelete);
	}

	@Override
	public void deleteAll(List<Content> contentsToDelete) {
		super.deleteAll(contentsToDelete);
	}

	@Override
	public boolean isLoaded() {
		return isContentsLoaded;
	}

	@Override
	public void setLoaded(boolean isLoaded) {
		isContentsLoaded = isLoaded;
	}
}
