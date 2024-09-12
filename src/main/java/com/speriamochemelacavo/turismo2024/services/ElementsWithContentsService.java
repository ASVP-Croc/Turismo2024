package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.ElementWithContents;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.models.users.User;

@Service
public abstract class ElementsWithContentsService<T extends ElementWithContents> extends ElementsService<T>{
	
	public ElementsWithContentsService(){
		super();
	}

	@Override
	public void add(T elementsWithContentsToAdd, User author) {
		super.add(elementsWithContentsToAdd, author);
	}

	@Override
	public void add(T elementsWithContentsToAdd, User author, List<Tag> tags) {
		super.add(elementsWithContentsToAdd, author, tags);
	}
	
	public void add(T elementsWithContentsToAdd, User author, List<Tag> tags, List<Content> contentToAdd) {
		elementsWithContentsToAdd.getMyContents().addAll(contentToAdd);
		super.add(elementsWithContentsToAdd, author, tags);
	}
}
