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