package com.mvpmatch.maze.api.repository;

import java.util.Optional;

import com.mvpmatch.maze.api.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Boolean existsByEmail(String email);
	
	Optional<User> findByEmail(String email);
}