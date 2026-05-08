package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.SavedJob;
import com.example.Repository.SavedJobRepository;

@Service
public class SavedJobService {
	@Autowired
	private SavedJobRepository savedJobRepository;
	
	public SavedJob saveJob(SavedJob savedJob) {
		return savedJobRepository.save(savedJob);
	}
}
