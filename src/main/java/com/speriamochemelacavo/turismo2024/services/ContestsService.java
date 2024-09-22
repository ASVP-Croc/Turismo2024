package com.speriamochemelacavo.turismo2024.services;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.speriamochemelacavo.turismo2024.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.Contest;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;

@Service
public class ContestsService extends ElementsWithContentsService<Contest>  {
	
	private boolean isContestsLoaded;

	public ContestsService() {
		super();
	}
	
	public void add(Contest contestToAdd, LocalDate starts, LocalDate ends) {
		contestToAdd.setStarts(starts);
		contestToAdd.setEnds(ends);
		super.add(contestToAdd);
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
