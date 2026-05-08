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