package com.example.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Entity.Application;
import com.example.Entity.Job;
import com.example.Entity.User;
import com.example.Repository.ApplicationRepository;

@Service
public class ApplicationService {
	@Autowired
	private ApplicationRepository applicationRepository;

	public Application applyJob( MultipartFile file,User user_id,Job job_id) {
		// TODO Auto-generated method stub
		Application app=new Application();
		app.setUser(user_id);
		app.setJob(job_id);
	
		try {
			app.setResume(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return applicationRepository.save(app);
	}

	public List<Application> getapplicants(int jobId) {
		// TODO Auto-generated method stub
		return applicationRepository.findByJobId(jobId);
	}
	
	
}
