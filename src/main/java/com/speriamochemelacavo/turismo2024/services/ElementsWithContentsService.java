package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.ElementWithContents;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.models.users.User;

@Service
public abstract class ElementsWithContentsService<T extends ElementWithContents> extends ElementsService<T>{
	
	@Autowired
	ContentsService contentService;
	
	public ElementsWithContentsService(){
		super();
	}

	public void add(T elementsWithContentsToAdd, User author, List<Tag> tags, List<Content> contentToAdd) {
		elementsWithContentsToAdd.getMyContents().addAll(contentToAdd);
		super.add(elementsWithContentsToAdd, author, tags);
	}

	@Override
	public void deleteById(Integer id) {
		super.deleteById(id);
	}

	@Override
	public void delete(T elementsWithContentsToDelete) {
		super.delete(elementsWithContentsToDelete);
	}

	@Override
	public void deleteAll(List<T> elementsWithContentsToDelete) {
		super.deleteAll(elementsWithContentsToDelete);
	}
	
	public void deleteMyContent(T elementWithContent, Content content) {
		elementWithContent.getMyContents().remove(content);
		//update
		contentService.delete(content);
	}
	
	
}
