package com.mvpmatch.maze.api.util;

import java.util.ArrayList;
import java.util.List;

public class Util {
    
    static List<List<String>> paths = new ArrayList<List<String>>();
    static String path = "";
    static List<String> subpath = new ArrayList<String>();
     // Check if cell (x, y) is valid or not
    private static boolean isValidCell(int x, int y, int N) {
        return !(x < 0 || y < 0 || x >= N || y >= N);
    }
 
    private static int countPaths(int[][] maze, int i, int j, int x, int y,
                                  boolean visited[][])
    {
        // if destination (x, y) is found, return 1
        if (i == x && j == y) {
            path = i+""+j;
            subpath.add(path);
            paths.add(subpath);
            subpath = new ArrayList<String>();
            return 1;
        }
 
        // stores number of unique paths from source to destination
        int count = 0;
 
        // mark the current cell as visited
        visited[i][j] = true;
        path = i+""+j;
        subpath.add(path);
            
        // `N × N` matrix
        int N = maze.length;
 
        // if the current cell is a valid and open cell
        if (isValidCell(i, j, N) && maze[i][j] == 1)
        {
            // go down (i, j) ——> (i + 1, j)
            if (i + 1 < N && !visited[i + 1][j]) {
                // path = i+""+j;
                // subpath.add(path);
                count += countPaths(maze, i + 1, j, x, y, visited);
            }
 
            // go up (i, j) ——> (i - 1, j)
            if (i - 1 >= 0 && !visited[i - 1][j]) {
                count += countPaths(maze, i - 1, j, x, y, visited);
            }
 
            // go right (i, j) ——> (i, j + 1)
            if (j + 1 < N && !visited[i][j + 1]) {
                count += countPaths(maze, i, j + 1, x, y, visited);
            }
 
            // go left (i, j) ——> (i, j - 1)
            if (j - 1 >= 0 && !visited[i][j - 1]) {
                count += countPaths(maze, i, j - 1, x, y, visited);
            }
        }
 
        // backtrack from the current cell and remove it from the current path
        visited[i][j] = false;
 
        return count;
    }
 
    public static int findCount(int[][] maze, int i, int j, int x, int y)
    {
        // base case: invalid input
        if (maze == null || maze.length == 0 || maze[i][j] == 0 || maze[x][y] == 0) {
            return 0;
        }
 
        // `N × N` matrix
        int N = maze.length;
 
        // 2D matrix to keep track of cells involved in the current path
        boolean[][] visited = new boolean[N][N];
 
        // start from source cell (i, j)
        return countPaths(maze, i, j, x, y, visited);
    }
 
}
