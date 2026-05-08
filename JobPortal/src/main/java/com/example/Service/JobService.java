package com.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Job;
import com.example.Repository.JobRepository;

@Service
public class JobService {
@Autowired
private JobRepository jobRepository;

	public Job addJob(Job job) {
		return jobRepository.save(job);
	}

	public List<Job> getAllJobs() {
		// TODO Auto-generated method stub
		return jobRepository.findAll();
	}
	public Job getJobById(int id) {
		return jobRepository.findById(id).orElse(null);
	}

	
	public void delete(int id) {
		 jobRepository.deleteById(id);
	}

	public List<Job> getJobByHr(int hrId) {
		// TODO Auto-generated method stub
		return jobRepository.findByHr_Id(hrId);
	}
}
