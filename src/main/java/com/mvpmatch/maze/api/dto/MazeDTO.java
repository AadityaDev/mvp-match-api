package com.mvpmatch.maze.api.dto;

import java.util.List;

public class MazeDTO {
    
    private Long id;
    public String entrance;
    public String gridsize;
    public List<String> walls;
    
    public String getEntrance() {
        return entrance;
    }

    public String getGridsize() {
        return gridsize;
    }

    public List<String> getWalls() {
        return walls;
    }
}
