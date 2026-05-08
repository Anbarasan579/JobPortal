package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.HR;

public interface HrRepository extends JpaRepository<HR,Integer>{

	HR findByEmail(String email);

}
