package com.speriamochemelacavo.turismo2024.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.Contest;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.models.users.User;

@Service
public class ContestsService extends ElementsWithContentsService<Contest>  {
	
	public ContestsService() {
		super();
	}

	@Override
	public void add(Contest contestToAdd, User author) {
		super.add(contestToAdd, author);
	}

	@Override
	public void add(Contest contestToAdd, User author, List<Tag> tags) {
		super.add(contestToAdd, author, tags);
	}
	
	public void add(Contest contestToAdd, User author, List<Tag> tags, Date starts, Date ends) {
		contestToAdd.setStarts(starts);
		contestToAdd.setEnds(ends);
		super.add(contestToAdd, author, tags);
	}
	
	
}
