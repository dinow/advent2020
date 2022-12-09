package be.dno.advent2022;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import be.dno.Day;


public class Day09 extends Day {
   Set<Point> visitedByTail;
   public Day09(){
      fileName = "2022/day09.txt";
   }

   @Override
   public void fillDataStruct() {
      visitedByTail = new HashSet<>();
   }

   private void movePoint(Point[] rope, int k, int newX, int newY){
      rope[k].setLocation(newX, newY);
   }

   private int processLines(Point[] rope){
      for (String line : lines){
         String[] commands = line.split(" ");
         int steps = Integer.valueOf(commands[1]);
         for (int i = 0; i < steps; i++){
            //Move last item of array (== head)
            if (commands[0].equals("R")){
               rope[rope.length-1].setLocation(rope[rope.length-1].getX()+1, rope[rope.length-1].getY()  );
            }else if (commands[0].equals("U")){
               rope[rope.length-1].setLocation(rope[rope.length-1].getX()  , rope[rope.length-1].getY()-1);
            }else if (commands[0].equals("L")){
               rope[rope.length-1].setLocation(rope[rope.length-1].getX()-1, rope[rope.length-1].getY()  );
            }else if (commands[0].equals("D")){
               rope[rope.length-1].setLocation(rope[rope.length-1].getX()  , rope[rope.length-1].getY()+1);
            }

            //Move all other knots
            for (int k = rope.length-2; k >= 0; k--){
               //Compute distance to next knot
               int dY = Double.valueOf(Math.max(rope[k+1].getY(), rope[k].getY()) - Math.min(rope[k+1].getY(), rope[k].getY())).intValue();
               int dX = Double.valueOf(Math.max(rope[k+1].getX(), rope[k].getX()) - Math.min(rope[k+1].getX(), rope[k].getX())).intValue();
               if (dY > 1 || dX > 1){
                  //Move tail as we are too far behind
                  int yStep = Double.valueOf(rope[k+1].getY() - rope[k].getY()).intValue();
                  int xStep = Double.valueOf(rope[k+1].getX() - rope[k].getX()).intValue();

                  //Make sure we only move one step
                  if (yStep < -1) yStep = -1;
                  if (yStep >  1) yStep = 1;

                  if (xStep < -1) xStep = -1;
                  if (xStep >  1) xStep = 1;

                  //Store new position for current knot (default == current values)
                  int newX = Double.valueOf(rope[k].getX()).intValue();
                  int newY = Double.valueOf(rope[k].getY()).intValue();;

                  if (dX == 0){ //Move Y as we stayed on the same column
                     newY = Double.valueOf(rope[k].getY() + yStep).intValue();
                  }else if (dY == 0){ //Move X as we stayed on same row
                     newX = Double.valueOf(rope[k].getX()+xStep).intValue();
                  } else if ((dX == 1 && dY == 2) || (dX == 2 && dY == 1) || (dX == 2 && dY == 2)){
                     //Move diagonally as both values are positives
                     if (dX == 1 && dY == 2){
                        newX = Double.valueOf(rope[k+1].getX()).intValue();
                        newY = Double.valueOf(rope[k].getY()+yStep).intValue();
                     } else if (dX == 2 && dY == 1){
                        newX = Double.valueOf(rope[k].getX()+xStep).intValue();
                        newY = Double.valueOf(rope[k+1].getY()).intValue();
                     } else if (dX == 2 && dY == 2){
                        newX = Double.valueOf(rope[k].getX()+xStep).intValue();
                        newY = Double.valueOf(rope[k].getY()+yStep).intValue();
                     }
                  }else{
                     System.out.println("ERROR: dX = " + dX + ", dY = " + dY);
                  }
                  //Actually move the current k point
                  movePoint(rope, k, Double.valueOf(newX).intValue(),Double.valueOf(newY).intValue());
               }
            }
            //Store tail position, always (it's a set, it's not efficient but why not...)
            visitedByTail.add(new Point(Double.valueOf(rope[0].getX()).intValue(), Double.valueOf(rope[0].getY()).intValue()));
         }
      }
      return visitedByTail.size();
   }


   @Override
   public String processPart1() {
      Point[] rope = new Point[2];
      for (int i = 0; i < rope.length; i++){
         rope[i] = new Point(0,0);
      }
      return String.valueOf(processLines(rope));
   }

   @Override
   public String processPart2() {
      Point[] rope = new Point[10];
      for (int i = 0; i < rope.length; i++){
         rope[i] = new Point(0,0);
      }
      return String.valueOf(processLines(rope));
   }
}

