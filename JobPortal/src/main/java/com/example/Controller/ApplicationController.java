package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Entity.Application;
import com.example.Entity.Job;
import com.example.Entity.User;
import com.example.Service.ApplicationService;

@RestController
@RequestMapping("/AppCtrl")
public class ApplicationController {
	@Autowired
	private ApplicationService applicationService;
	
	@PostMapping("/apply")
	public Application apply(@RequestParam MultipartFile file,@RequestParam User user_id,@RequestParam Job job_id) {
		return applicationService.applyJob(file,user_id,job_id);
	}
	@GetMapping("/applicants/{jobId}")
	public List<Application>getApplicants(@PathVariable int jobId){
		return applicationService.getapplicants(jobId);
	}
}
