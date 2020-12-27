package be.dno.advent2016;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


import be.dno.Day;
import be.dno.Utils;

public class Day13 extends Day{
   private int inputNumber;
   private static final int ROW = 50;
   private static final int COL = 50;
   private char[][] maze = new char[ROW][COL];
   private boolean[][] visited = new boolean[ROW][COL];

   // These arrays are used to get row and column
   // numbers of 4 neighbours of a given cell
   private static int rowNum[] = {-1, 0, 0, 1};
   private static int colNum[] = {0, -1, 1, 0};

   public Day13(){
      fileName = "2016/day13.txt";
   }

   public void fillDataStruct(){
      inputNumber = Integer.parseInt(lines.get(0));
      for (int x = 0; x < ROW; x++){
         for (int y = 0; y < COL; y++){
            maze[x][y] = getMatrixChar(addInput(formula(y, x)));
         }
      }
   }

   @Override
   public String processPart1() {
      return ""+getDistance(39, 31);
   }

   public int getDistance(int x, int y){
      Utils.initMatrix(visited, false);
      visited[1][1] = true;
      Queue<Node> q = new LinkedList<>();
     
      // Distance of source cell is 0
      Node s = new Node(1, 1, 0);
      q.add(s); // Enqueue source cell
 
      // Do a BFS starting from source cell
      while (!q.isEmpty()){
         Node curr = q.peek();
 
         // If we have reached the destination cell,
         // we are done
         if (curr.x == x && curr.y == y){
             return curr.distanceFromSource;
         }
 
         // Otherwise dequeue the front cell 
         // in the queue and enqueue
         // its adjacent cells
         q.remove();
 
         for (int i = 0; i < 4; i++){
            int row = curr.x + rowNum[i];
            int col = curr.y + colNum[i];
             
            // if adjacent cell is valid, has path 
            // and not visited yet, enqueue it.
            if (isValid(row, col) && maze[row][col] == '.' && !visited[row][col]){
                // mark cell as visited and enqueue it
                visited[row][col] = true;
                Node Adjcell = new Node(row, col,curr.distanceFromSource + 1 );
                q.add(Adjcell);
            }
         }
      }
      return -1;
   }

   @Override
   public String processPart2() {
      int maxDist = 0;
      for (int x = 0; x < ROW; x++){
         for (int y = 0; y < COL; y++){
            if (maze[x][y] == '.'){
               int dist = getDistance(x, y);
               if (dist > -1 && dist <= 50) {
                  maxDist++;
               }
            }
         }
      }
      return ""+maxDist;
   }

   public int formula(int x, int y){
      return x*x + 3*x + 2*x*y + y + y*y;
   }

   public int addInput(int in){
      return in+inputNumber;
   }

   public char getMatrixChar(int in){
      String binary = Integer.toBinaryString(in);
      long count = binary.chars().filter(ch -> ch == '1').count();
      if ( count % 2 == 0 ) {
         return '.';
      } else {
         return '#';
      }
   }

   private boolean isValid(int row, int col){
      // return true if row number and 
      // column number is in range
      return (row >= 0) && (row < ROW) &&
             (col >= 0) && (col < COL);
   }
 



}

class Node {
   int x;
   int y;
   int distanceFromSource;
   
   Node(int x, int y, int dis) {
       this.x = x;
       this.y = y;
       this.distanceFromSource = dis;
   }
}