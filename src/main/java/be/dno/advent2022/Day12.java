package be.dno.advent2022;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import be.dno.Day;


public class Day12 extends Day {
   int[][] matrix;
   int ROW;
   int COL;
   int rowStart = -1;
   int colStart = -1;
   int rowEnd = -1;
   int colEnd = -1;
   List<Cell> allA;


   public Day12(){
      fileName = "2022/day12.txt";
   }

   static class Cell{
      int row;
      int col;
      int distance;

      Cell(int row, int col, int distance){
          this.row = row;
          this.col = col;
          this.distance = distance;
      }
   }

   private int minDistance(){
      Cell source = new Cell(rowStart, colStart, 0);

      // applying BFS on matrix cells starting from source
      Queue<Cell> queue = new LinkedList<>();
      queue.add(new Cell(source.row, source.col, 0));

      boolean[][] visited = new boolean[matrix.length][matrix[0].length];
      visited[source.row][source.col] = true;

      while (queue.isEmpty() == false) {
         Cell p = queue.remove();

         // Destination found;
         if (p.row == rowEnd && p.col == colEnd){
            //System.out.println("min distance from ["+rowStart+","+colStart+"] to ["+rowEnd+","+colEnd+"] is ["+p.distance+"]");
            return p.distance;
         }

         // moving up
         if (isValid(p, p.row - 1, p.col, matrix, visited)) {
            queue.add(new Cell(p.row - 1, p.col, p.distance + 1));
            visited[p.row - 1][p.col] = true;
         }

         // moving down
         if (isValid(p, p.row + 1, p.col, matrix, visited)) {
            queue.add(new Cell(p.row + 1, p.col, p.distance + 1));
            visited[p.row + 1][p.col] = true;
         }

         // moving left
         if (isValid(p, p.row, p.col - 1, matrix, visited)) {
           queue.add(new Cell(p.row, p.col - 1, p.distance + 1));
           visited[p.row][p.col - 1] = true;
         }

         // moving right
         if (isValid(p, p.row, p.col + 1, matrix, visited)) {
           queue.add(new Cell(p.row, p.col + 1, p.distance + 1));
           visited[p.row][p.col + 1] = true;
         }
      }
      return -1;
   }

   // checking where it's valid or not
   private boolean isValid(Cell p, int row, int col, int[][] grid, boolean[][] visited) {
      if (row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && visited[row][col] == false){
         int stepValue = matrix[p.row][p.col] - matrix[row][col];
         if (stepValue >= -1){
            return true;
         }
      }
      return false;
   }

   @Override
   public void fillDataStruct() {
      allA = new ArrayList<>();
      matrix = new int[lines.size()][lines.get(0).length()];
      ROW = matrix.length;
      COL = matrix[0].length;
      int i =  0;
      for (String line : lines){
        int j = 0;
          for (String c : line.split("")){
            if (c.equals("S")){
               rowStart = i;
               colStart = j;
               c="a";
            }
            if (c.equals("E")){
               rowEnd = i;
               colEnd = j;
               c="z";
            }
            matrix[i][j] = (int)c.charAt(0);
            if (c.equals("a")){
               allA.add(new Cell(i, j, 0));
            }
            j++;
         }
         i++;
      }
   }

   @Override
   public String processPart1() throws Exception {
      return String.valueOf(minDistance());
   }

   @Override
   public String processPart2() throws Exception {
      int minDistance = Integer.MAX_VALUE;
      for (Cell a : allA){
         rowStart = a.row;
         colStart = a.col;
         int minCurDist = minDistance();
         if (minCurDist > -1){
            if (minCurDist < minDistance){
               minDistance = minCurDist;
            }
         }
      }
      return String.valueOf(minDistance);
   }
}

