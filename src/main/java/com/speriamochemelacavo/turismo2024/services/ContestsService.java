package com.speriamochemelacavo.turismo2024.services;

import java.time.LocalDate;
import java.util.List;
import com.speriamochemelacavo.turismo2024.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.content.Content;
import com.speriamochemelacavo.turismo2024.models.elements.Contest;

@Service
public class ContestsService extends ElementsWithContentsService<Contest>  {
	
	@Autowired
	private ContestRepository contestRepository;
	
	private boolean isContestsLoaded;

	public ContestsService() {
		super();
	}
	
	@Override
	public List<Contest> findAll() {
		return contestRepository.findAll();
		
	}

	@Override
	public boolean isLoaded() {
		return isContestsLoaded;
	}

	@Override
	public void setLoaded(boolean isLoaded) {
		isContestsLoaded = isLoaded;
	}
	
	@Override
	public void addContentToElement(Contest contest, Content content) {
		LocalDate date = LocalDate.now();
		if(date.isBefore(contest.getEnds())) {
			super.addContentToElement(contest, content);
		}
	}
	
	
}
