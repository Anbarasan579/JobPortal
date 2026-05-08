package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.HR;
import com.example.Service.HrService;

@RestController
@RequestMapping("/HrCtrl")
public class HrController {
	
	@Autowired
	private HrService hrService;

	@GetMapping("/run")
	public String run() {
		return "running...";
	}	
		@PostMapping("/register")
		public HR register(@RequestBody HR hr) {
			return hrService.register(hr);
		
	}
		
		@PostMapping("/login")
		public String login(@RequestParam String email,@RequestParam String password) {
			HR hr=hrService.login(email,password);
			if(hr!=null) {
				return" HR login successful";
			}
			else {
				return"Invalid  HR login";
			}
		}
		@PutMapping("/{id}")
		public HR change(@PathVariable int id,@RequestBody HR hr) {
			return hrService.update(id,hr);
		}
		
}
