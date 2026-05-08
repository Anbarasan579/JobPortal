package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Job;

public interface JobRepository extends JpaRepository<Job,Integer> {

	List<Job> findByHr_Id(int hrId);	
}
