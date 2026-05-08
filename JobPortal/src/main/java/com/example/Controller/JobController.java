package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.HR;
import com.example.Entity.Job;
import com.example.Repository.HrRepository;
import com.example.Service.JobService;

@RestController
@RequestMapping("/JobCtrl")
public class JobController {
	@Autowired
	private JobService jobService;
	
	@Autowired 
	private HrRepository hrRepository;
	@PostMapping("/add")
	public Job addJob(@RequestParam int hrId,@RequestBody Job job) { 
		HR hr=hrRepository.findById(hrId).orElse(null);
		job.setHr(hr);
		return jobService.addJob(job);
	}
	
	@GetMapping("/all")
	public List<Job>getAllJobs(){
		return jobService.getAllJobs();
	}
	@GetMapping("/{id}")
	public Job getJob(@PathVariable int id) {
		return jobService.getJobById(id);
	}
	@DeleteMapping("/{id}")
	public String deleteJob(@PathVariable int id) {
		 jobService.delete(id);
		return "Deleted";
	}
	
	@GetMapping("/hr")
		public List<Job> getJobByHr(@RequestParam int hrId){
			return jobService.getJobByHr(hrId);
		
	}
}