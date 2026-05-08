package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.SavedJob;
import com.example.Service.SavedJobService;

@RestController
@RequestMapping("/SavedJobCtrl")
public class SavedJobController {
	@Autowired
	private SavedJobService savedJobService;
	
	@PostMapping("/save")
	public SavedJob saveJob(@RequestBody SavedJob savedJob) {
		return savedJobService.saveJob(savedJob);
	}
}
