package com.mvpmatch.maze.api.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class QItem {
    int row;
    int col;
    int dist;
    public QItem(int row, int col, int dist)
    {
      this.row = row;
      this.col = col;
      this.dist = dist;
    }
  }

public class PathUtilMin {
 
// QItem for current location and distance
// from source locatio
    private static int minDis = Integer.MIN_VALUE;
    private static int maxDis = Integer.MAX_VALUE;

    private static List<String> minPath = new ArrayList<String>();

    public static List<String> getMinPath() {
      return minPath;
    }
  public static int minDistance(char[][] grid, String[][] st)
  {
    QItem source = new QItem(0, 0, 0);
     
    // To keep track of visited QItems. Marking
    // blocked cells as visited.
    firstLoop:
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++)
      {
         
        // Finding source
        if (grid[i][j] == 's') {
          source.row = i;
          source.col = j;
          System.out.print(grid[i][j]+ "=>");
          minPath.add(st[i][j]);
          break firstLoop;
        }
      }
    }
     
    // applying BFS on matrix cells starting from source
    Queue<QItem> queue = new LinkedList<>();
    queue.add(new QItem(source.row, source.col, 0));
 
    boolean[][] visited
      = new boolean[grid.length][grid[0].length];
    visited[source.row][source.col] = true;
 
    while (queue.isEmpty() == false) {
      QItem p = queue.remove();
       
      // Destination found;
      if (grid[p.row][p.col] == 'd') {
        System.out.print(grid[p.row][p.col]+ "=>");
        minPath.add(st[p.row][p.col]);
        minDis = p.dist;
        return p.dist;
      }
        
 
      // moving up
      if (isValid(p.row - 1, p.col, grid, visited)) {
        queue.add(new QItem(p.row - 1, p.col,
                            p.dist + 1));
        visited[p.row - 1][p.col] = true;
        minPath.add(st[p.row-1][p.col]);
        System.out.print(grid[p.row-1][p.col]+ "=>");
      }
 
      // moving down
      if (isValid(p.row + 1, p.col, grid, visited)) {
        queue.add(new QItem(p.row + 1, p.col,
                            p.dist + 1));
        visited[p.row + 1][p.col] = true;
        minPath.add(st[p.row+1][p.col]);
        System.out.print(grid[p.row+1][p.col]+ "=>");

      }
 
      // moving left
      if (isValid(p.row, p.col - 1, grid, visited)) {
        queue.add(new QItem(p.row, p.col - 1,
                            p.dist + 1));
        visited[p.row][p.col - 1] = true;
        minPath.add(st[p.row][p.col-1]);
        System.out.print(grid[p.row][p.col-1]+ "=>");

      }
 
      // moving right
      if (isValid(p.row, p.col + 1, grid,
                  visited)) {
        queue.add(new QItem(p.row, p.col + 1,
                            p.dist + 1));
        visited[p.row][p.col + 1] = true;
        minPath.add(st[p.row][p.col+1]);
        System.out.print(grid[p.row][p.col+1]+ "=>");

      }
    }
    return -1;
  }
   
  // checking where it's valid or not
  private static boolean isValid(int x, int y,
                                 char[][] grid,
                                 boolean[][] visited)
  {
    if (x >= 0 && y >= 0 && x < grid.length
        && y < grid[0].length && grid[x][y] != '0'
        && visited[x][y] == false) {
      return true;
    }
    return false;
  }
   
  // Driver code
  // public static void main(String[] args)
  // {
  //   char[][] grid = { 
  //       { 's', '0', '1', '1', '1', '1', '1', '1' },
  //       { '1', '1', '1', '0', '0', '0', '0', '1' },
  //       { '0', '0', '0', '0', '1', '1', '1', '1' },
  //       { '1', '1', '1', '1', '1', '0', '0', '1' },
  //       { '1', '0', '0', '0', '0', '0', '1', '1' },
  //       { '1', '1', '1', '0', '1', '1', '1', '1' },
  //       { '0', '0', '1', '0', '1', '0', '0', '1' },
  //       { '1', '1', '1', '1', '1', '0', '1', 'd' }
  //   };

  //   System.out.println(minDistance(grid));
  // }

}
