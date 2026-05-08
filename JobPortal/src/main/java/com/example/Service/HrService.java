package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.HR;
import com.example.Repository.HrRepository;

@Service
public class HrService {

	@Autowired
	private HrRepository HrRepo;
	
	public HR register(HR hr) {
		return HrRepo.save(hr);
	}
	public HR login(String email, String password) {
		HR hr=HrRepo.findByEmail(email);
		if(hr!=null&&hr.getPassword().equals(password)) {
			return hr;
		}
		return null;
	}
	public HR update(int id,HR hr) {
		HR newHr=HrRepo.findById(id).orElse(null);
		newHr.setName(hr.getName());
		newHr.setEmail(hr.getEmail());
		newHr.setPassword(hr.getPassword());
		newHr.setCompany(hr.getCompany());
		
		return HrRepo.save(newHr);
	}
	
}
