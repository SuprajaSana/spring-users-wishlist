package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	@Query("SELECT u FROM Users u WHERE u.email=?1 ")
	Users findByEmail(String email);
	
	@Query("SELECT p FROM Users p WHERE p.password=?1 ")
	Users findByPassword(String password);
	
}
