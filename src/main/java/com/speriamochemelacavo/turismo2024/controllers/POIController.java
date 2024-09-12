package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.services.ContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.services.POIsService;

@RestController
public class POIController {
	
	@Autowired
	private POIsService poiService;

	@Autowired
	private ContentsService contentsService;

	@GetMapping("poi/{id}")
	public PointOfInterest getPOIById(@PathVariable int id) {
		return poiService.findById(id);
	}
<<<<<<< Updated upstream
=======
<<<<<<< HEAD

	@PostMapping("poi")
	public void savePoi(PointOfInterest poiToAdd, User author) {
		poiService.add(poiToAdd, author);
	}

	@PutMapping("/poi/{id}")
	public void updatePoi(@RequestBody PointOfInterest pointOfInterestToUpdate) {
		poiService.update(pointOfInterestToUpdate);
	}

	@DeleteMapping("/poi/{id}")
	public void deletePoiById(@PathVariable Integer id) {
		poiService.deleteById(id);
	}

	@GetMapping("poi/content/{id}")
	public void getContent(@RequestBody PointOfInterest pointOfInterest, @PathVariable Integer id, @RequestBody Content contentToGet) {
		contentsService.findById(id);

	}

	@PostMapping("poi")
	public void createContent(@RequestBody PointOfInterest pointOfInterest, @RequestBody Content contentToAdd, @RequestBody User author) {

	}

	@PutMapping("poi/content")
	public void updateContent(@RequestBody PointOfInterest pointOfInterest, @RequestBody Content contentToUpdate) {
		contentsService.update(contentToUpdate);
		pointOfInterest.getMyContents().add(contentToUpdate);
	}
=======
>>>>>>> ecd8b9d3b0402c476fcfccddbb41692504078ccc
>>>>>>> Stashed changes
}
