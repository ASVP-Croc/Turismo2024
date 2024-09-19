package com.speriamochemelacavo.turismo2024.services;

import java.util.Date;
import java.util.List;

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

	@Override
	public void add(Contest contestToAdd) {
		super.add(contestToAdd);
	}

	@Override
	public void add(Contest contestToAdd, List<Tag> tags) {
		super.add(contestToAdd, tags);
	}
	
	public void add(Contest contestToAdd, List<Tag> tags, Date starts, Date ends) {
		contestToAdd.setStarts(starts);
		contestToAdd.setEnds(ends);
		super.add(contestToAdd, tags);
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
		Date date = new Date();
		if(date.before(contest.getEnds())) {
			super.addContentToElement(contest, content);
		}
	}
	
	
}
