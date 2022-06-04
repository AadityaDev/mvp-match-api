package com.mvpmatch.maze.api.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.mvpmatch.maze.api.model.MazeWall;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MazeWallRepository extends JpaRepository<MazeWall, Long> {
		
	Optional<MazeWall> findById(Long id);

	Optional<MazeWall> findByMazeId(Long id);
	
	@Query(nativeQuery = true, value = "SELECT * FROM demodb.tbl_maze_wall WHERE maze_id=:mazeId")
	Optional<List<MazeWall>>findAllByMazeId(@Param("mazeId") Long id);
}