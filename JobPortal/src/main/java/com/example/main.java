/**


--application.properties
spring.application.name=JobPortal
spring.datasource.url=jdbc:mysql://localhost:3306/jobportal
spring.datasource.username=root
spring.datasource.password=root1234

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.jpa.properties.hibernate.format_sql=true


--CONTROLLER Class

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

import com.example.Entity.User;
import com.example.Service.UserService;

@RestController
@RequestMapping("/UserCtrl")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/test")
	public String test() {
		return "working.....";
	}
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		return userService.register(user);
	}
	@GetMapping("/{id}")
	public User getUser(@PathVariable int id) {
		return userService.getById(id);
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String email,@RequestParam String password) {
		User user=userService.login(email,password);
		if(user!=null) {
			return"login successful";
		}
		else {
			return"Invalid login";
		}
	}
	@PutMapping("/{id}")
	public User change(@PathVariable int id ,@RequestBody User user) {
		return userService.update(id,user);
	}
}



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



--SERVICE Class

package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.User;
import com.example.Repository.UserRepository;
@Service
public class UserService {

	@Autowired 
	private UserRepository userRepository;

	public User register(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}
	
		public User getById(int id) {
			return userRepository.findById(id).orElse(null);
		}
	public User login(String email, String password) {
		User user=userRepository.findByEmail(email);
		if(user.getEmail().equals(email)&&user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
	public User update(int id ,User user) {
		User newUser=userRepository.findById(id).orElse(null);
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		return userRepository.save(newUser);
	}
}



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
		return jobRepository.getJobsByHr(hrId);
	}
}


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


--REPOSITORY

package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{

	User findByEmail(String email);

}


package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.HR;

public interface HrRepository extends JpaRepository<HR,Integer>{

	HR findByEmail(String email);

}


package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Job;

public interface JobRepository extends JpaRepository<Job,Integer> {

	List<Job> getJobsByHr(int hrId);
	
}


package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Application;

public interface ApplicationRepository extends JpaRepository<Application,Integer> {

	List<Application> findByJobId(int jobId);


}


package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.SavedJob;

public interface SavedJobRepository extends JpaRepository<SavedJob,Integer>{
	

}


--ENTITY Class


package com.example.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String password;

    public User() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

   
}

package com.example.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="HR")
public class HR {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		
		private String name;
		private String email;
		private String password;
		private String company;
		
		public HR() {}
		
		public int getId() { return id; }
		public void setId(int id) { this.id=id; }

		public String getName() {	return name;}
		public void setName(String name) {	this.name = name;}

		public String getEmail() {	return email;}
		public void setEmail(String email) {	this.email = email;}

		public String getPassword() {	return password;}
		public void setPassword(String password) {	this.password = password;}

		public String getCompany() {	return company;}
		public void setCompany(String company) {	this.company = company;}
}

package com.example.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    
    
    private String title;
    private String company;
    private String location;
    private double salary;

    @ManyToOne
    @JoinColumn(name="hr_id")
    private HR hr;
    
    
    public Job() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

	public HR getHr() {	return hr;}
	public void setHr(HR hr) {	this.hr = hr;}
    
    
}

package com.example.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name="job_id")
    private Job job;
    
  //  private int userId;
   // private int jobId;
    
    private byte[] resume;

    public Application() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

  //  public int getUserId() { return userId; }
 //   public void setUserId(int userId) { this.userId = userId; }

//    public int getJobId() { return jobId; }
  //  public void setJobId(int jobId) { this.jobId = jobId; }
    

	public byte[] getResume() {
		return resume;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public void setResume(byte[] resume) {
		this.resume = resume;
	}

	
    
}

package com.example.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SavedJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private User userId;
    
    @ManyToOne
    @JoinColumn(name="job_id")
    private Job jobId;

    public SavedJob() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public User getUserId() { return userId; }
    public void setUserId(User userId) { this.userId = userId; }

    public Job getJobId() { return jobId; }
    public void setJobId(Job jobId) { this.jobId = jobId; }
}








--pom.xml


<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>4.0.6</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.example</groupId>
	<artifactId>JobPortal</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>JobPortal</name>
	<description/>
	<url/>
	<licenses>
		<license/>
	</licenses>
	<developers>
		<developer/>
	</developers>
	<scm>
		<connection/>
		<developerConnection/>
		<tag/>
		<url/>
	</scm>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webmvc</artifactId>
		</dependency>

		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webmvc-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<executions>
					<execution>
						<id>default-compile</id>
						<phase>compile</phase>
						<goals>
							<goal>compile</goal>
						</goals>
						<configuration>
							<annotationProcessorPaths>
								<path>
									<groupId>org.projectlombok</groupId>
									<artifactId>lombok</artifactId>
								</path>
							</annotationProcessorPaths>
						</configuration>
					</execution>
					<execution>
						<id>default-testCompile</id>
						<phase>test-compile</phase>
						<goals>
							<goal>testCompile</goal>
						</goals>
						<configuration>
							<annotationProcessorPaths>
								<path>
									<groupId>org.projectlombok</groupId>
									<artifactId>lombok</artifactId>
								</path>
							</annotationProcessorPaths>
						</configuration>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>

</project>


**/