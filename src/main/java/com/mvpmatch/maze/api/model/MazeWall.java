package com.mvpmatch.maze.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tbl_maze_wall")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MazeWall {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    public Long mazeId;
    public String wall;
    // @ManyToOne
    // private Maze maze;

    public Long getMazeId() {
        return mazeId;
    }

    public String getWall() {
        return wall;
    }
    
}
