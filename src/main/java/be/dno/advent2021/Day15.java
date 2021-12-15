package be.dno.advent2021;

import java.util.Comparator;
import java.util.PriorityQueue;

import be.dno.Day;
import be.dno.Utils;

public class Day15 extends Day {

    long[][] matrix;
    int ROW;
    int COL;
    
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public Day15(){
        fileName = "2021/day15.txt";
    }
    
    static class Cell{
        int x;
        int y;
        long distance;
         
        Cell(int x, int y, long distance){
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
     
    // Custom comparator for inserting cells
    // into Priority Queue
    static class distanceComparator implements Comparator<Cell>{
        public int compare(Cell a, Cell b){
            if (a.distance < b.distance){
                return -1;
            } else if (a.distance > b.distance){
                return 1;
            } else {
                return 0;
            }
        }
    }
     
    // Utility method to check whether current
    // cell is inside grid or not
    boolean isInsideGrid(int i, int j){
        return (i >= 0 && i < ROW && j >= 0 && j < COL);
    }
     
    // Method to return shortest path from
    // top-corner to bottom-corner in 2D grid
    long shortestPath(long[][] grid, int row, int col){
        long[][] dist = new long[row][col];
         
        // Initializing distance array by INT_MAX
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                dist[i][j] = Long.MAX_VALUE;
            }
        }
         
        // Initialized source distance as
        // initial grid position value
        dist[0][0] = grid[0][0];
         
        PriorityQueue<Cell> pq = new PriorityQueue<Cell>(row * col, new distanceComparator());
                       
        // Insert source cell to priority queue
        pq.add(new Cell(0, 0, dist[0][0]));
        while (!pq.isEmpty()){
            Cell curr = pq.poll();
            for(int i = 0; i < 4; i++){
                int rows = curr.x + dx[i];
                int cols = curr.y + dy[i];
                 
                if (isInsideGrid(rows, cols)){
                    if (dist[rows][cols] > dist[curr.x][curr.y] + grid[rows][cols]) {
                         
                        // If Cell is already been reached once,
                        // remove it from priority queue
                        if (dist[rows][cols] != Integer.MAX_VALUE){
                            Cell adj = new Cell(rows, cols, dist[rows][cols]);         
                            pq.remove(adj);
                        }
                         
                        // Insert cell with updated distance
                        dist[rows][cols] = dist[curr.x][curr.y] + grid[rows][cols];
                                            
                        pq.add(new Cell(rows, cols, dist[rows][cols]));
                    }
                }
            }
        }
        return dist[row - 1][col - 1];
    }

   @Override
   public void fillDataStruct(){
      matrix = new long[lines.size()][lines.get(0).length()];
      int i =  0;
      for (String line : lines){
          int j = 0;
          for (String c : line.split("|")){
              matrix[i][j] = Long.valueOf(c).longValue();
              j++;
          }
          i++;
      }
   }
    
    

   @Override
   public String processPart1(){ 
      ROW = matrix.length;
      COL = matrix[0].length;
      return (shortestPath(matrix, ROW, COL)-matrix[0][0])+"";
   }
    
   @Override
   public String processPart2(){ 
      ROW = matrix.length * 5;
       COL = matrix[0].length * 5;
       int INIT_ROW = lines.size();
       int INIT_COL = lines.get(0).length();
        
        
      matrix = new long[lines.size()*5][lines.get(0).length()*5];
      Utils.initMatrix(matrix, 0l);
      int i =  0;
      for (String line : lines){
          int j = 0;
          for (String c : line.split("|")){
              matrix[i][j] = Long.valueOf(c).longValue();
              j++;
          }
          i++;
      }
      
      int initRow;
      int initCol;
      for (int cRow = 0; cRow < matrix.length; cRow++) {
         initRow = cRow;
         for(int cCol = 0; cCol < matrix[i].length; cCol++) {
            if (cCol >= INIT_COL){
               initCol = cCol - INIT_COL;
               initRow = cRow;
            } else {
               initCol = cCol;
               if (cRow >= INIT_ROW){
                  initRow = cRow - INIT_ROW;
               }
            }
            if (!(cRow < INIT_ROW && cCol < INIT_COL)){
               long plusOne = matrix[initRow][initCol] + 1;
               if (plusOne>9) plusOne=1;
               matrix[cRow][cCol] = plusOne;
            }
         }
      }
      return (shortestPath(matrix, matrix.length, matrix[0].length)-matrix[0][0])+"";
   }

}

