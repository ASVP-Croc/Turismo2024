package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.Contest;
import com.speriamochemelacavo.turismo2024.models.elements.ElementWithContents;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;

@Service
public abstract class ElementsWithContentsService<T extends ElementWithContents> extends ElementsService<T>{
	
	@Autowired
	private ContentsService<T> contentService;
	
	public List<Content> getContents() {
		findById(elementId).getMyContents().add(content);
		update(elementWithContent);
	}
	
	public void deleteContent(Content content, T contest) {
		contest.getMyContents().remove(content);
		update(contest);
	}
}
