package com.mvpmatch.maze.api.repository;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.List;
import java.util.Optional;

import com.mvpmatch.maze.api.model.Maze;
import com.mvpmatch.maze.api.model.MazeWall;

import org.json.JSONArray;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MazeRepository extends JpaRepository<Maze, Long> {
		
	Optional<Maze> findById(Long id);

	// Optional<Maze> findByWalls(List<String> walls); 

	Optional<Maze> findByGridsize(String gridsize);

	Optional<Maze> findByEntrance(String entrance);

}