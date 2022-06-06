package com.mvpmatch.maze.api.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PathUtilMax {

    private static int minDis = Integer.MAX_VALUE;
    private static int maxDis = Integer.MAX_VALUE;
    static int R = 8;
    static int C = 8;
    private static Set<String> maxPath = new HashSet<String>();

    public static void initRowColumn(int row, int column) {
        R = row;
        C = column;
    }

    public static Set<String> getMaxPath() {
        return maxPath;
    }
  // A Pair to store status of a cell. found is set to
  // true of destination is reachable and value stores
  // distance of longest path
  static class Pair {
 
    // true if destination is found
    boolean found;
 
    // stores cost of longest path from current cell to
    // destination cell
    int val;
 
    Pair (boolean x, int y){
      found = x;
      val = y;
    }
  }
 
  // Function to find Longest Possible Route in the
  // matrix with hurdles. If the destination is not reachable
  // the function returns false with cost Integer.MAX_VALUE.
  // (i, j) is source cell and (x, y) is destination cell.
 
  static Pair findLongestPathUtil (int mat[][], int i, int j, int x, int y, boolean visited[][], String[][] st) {
 
    // if (i, j) itself is destination, return true
    if(i == x && j == y)
    {
      maxPath.add(st[i][j]);
      return new Pair(true, 0);
    }
 
 
    // if not a valid cell, return false 
    if(i < 0 || i >= R || j < 0 || j >= C || mat[i][j] == 0 || visited[i][j] )
      return new Pair(false, Integer.MAX_VALUE);
 
    // include (i, j) in current path i.e.
    // set visited(i, j) to true
    visited[i][j] = true;
    maxPath.add(st[i][j]);
 
    // res stores longest path from current cell (i, j) to
    // destination cell (x, y)
    int res = Integer.MIN_VALUE;
 
    // go left from current cell
    Pair sol = findLongestPathUtil(mat, i, j-1, x, y, visited, st);
 
    // if destination can be reached on going left from current
    // cell, update res
    if(sol.found)
      res = Math.max(sol.val, res);
      maxDis = res;
      minDis = Math.min(sol.val, res);
 
    // go right from current cell
    sol = findLongestPathUtil(mat, i, j+1, x, y, visited, st);
 
    // if destination can be reached on going right from current
    // cell, update res
    if(sol.found)
      res = Math.max(sol.val, res);
      maxDis = res;
      minDis = Math.min(sol.val, res);

 
    // go up from current cell
    sol = findLongestPathUtil(mat, i-1, j, x, y, visited, st);
 
    // if destination can be reached on going up from current
    // cell, update res
    if(sol.found)
      res = Math.max(sol.val, res);
      maxDis = res;
      minDis = Math.min(sol.val, res);
 
    // go down from current cell
    sol = findLongestPathUtil(mat, i+1, j, x, y, visited, st);
 
    // if destination can be reached on going down from current
    // cell, update res
    if(sol.found)
      res = Math.max(sol.val, res);
      maxDis = res;
      minDis = Math.min(sol.val, res);
 
    // Backtrack
    visited[i][j] = false;
 
    // if destination can be reached from current cell,
    // return true
    if(res != Integer.MIN_VALUE)
      return new Pair(true, res+1);
 
    // if destination can't be reached from current cell,
    // return false
    else
      return new Pair(false, Integer.MAX_VALUE);
 
  } 
 
  // A wrapper function over findLongestPathUtil()
  public static void findLongestPath (int mat[][], int i, int j, int x, int y, String[][] st) {
 
    // create a boolean matrix to store info about
    // cells already visited in current route
    boolean visited[][] = new boolean[R][C];
 
 
    // find longest route from (i, j) to (x, y) and
    // print its maximum cost
    Pair p = findLongestPathUtil(mat, i, j, x, y, visited, st);
 
    if(p.found)
      System.out.println("Length of longest possible route is " + p.val);
 
    // If the destination is not reachable
    else
      System.out.println("Destination not reachable from given source");
 
  }
 
 
  // Driver Code
//   public static void main (String[] args) {
 
//     // input matrix with hurdles shown with number 0
//     int mat[][] = { 
//     { 1, 0, 1, 1, 1, 1, 1, 1},
//     { 1, 1, 1, 0, 0, 0, 0, 1 },
//     { 0, 0, 0, 0, 1, 1, 1, 1 },
//     { 1, 1, 1, 1, 1, 0, 0, 1 },
//     { 1, 0, 0, 0, 0, 0, 1, 1 },
//     { 1, 1, 1, 0, 1, 1, 1, 1 },
//     { 0, 0, 1, 0, 1, 0, 0, 1 },
//     { 1, 1, 1, 1, 1, 0, 1, 1 } 
//     };
 
//     // find longest path with source (0, 0) and
//     // destination (1, 7)
//     findLongestPath(mat, 0, 0, 7, 7);
//     System.out.println("min: "+minDis);
//     System.out.println("max: "+maxDis);
//   }
}
