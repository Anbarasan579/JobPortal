package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Application;

public interface ApplicationRepository extends JpaRepository<Application,Integer> {

	List<Application> findByJobId(int jobId);


}
