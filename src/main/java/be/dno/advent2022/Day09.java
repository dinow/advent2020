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


   @Override
   public String processPart1() {
      Point head = new Point(0,0);
      Point tail = new Point(0,0);
      for (String line : lines){
         String[] commands = line.split(" ");
         int steps = Integer.valueOf(commands[1]);
         for (int i = 0; i < steps; i++){
            if (commands[0].equals("R")){
               head.setLocation(head.getX()+1, head.getY());
            }else if (commands[0].equals("U")){
               head.setLocation(head.getX(), head.getY()-1);
            }else if (commands[0].equals("L")){
               head.setLocation(head.getX()-1, head.getY());
            }else if (commands[0].equals("D")){
               head.setLocation(head.getX(), head.getY()+1);
            }
            //System.out.println("H moved to " + head);
            //Compute distance between head and tail, and make follow
            int dY = Double.valueOf(Math.max(head.getY(), tail.getY()) - Math.min(head.getY(), tail.getY())).intValue();
            int dX = Double.valueOf(Math.max(head.getX(), tail.getX()) - Math.min(head.getX(), tail.getX())).intValue();
            if (dY > 1 || dX > 1){
               //Move tail
               if (dX == 0){ //Move Y
                  int yStep = Double.valueOf(head.getY() - tail.getY()).intValue();
                  if (yStep < -1) yStep = -1;
                  if (yStep >  1) yStep = 1;
                  tail.setLocation(tail.getX(), tail.getY() + yStep);
               }else if (dY == 0){ //Move X
                  int xStep = Double.valueOf(head.getX() - tail.getX()).intValue();
                  if (xStep < -1) xStep = -1;
                  if (xStep >  1) xStep = 1;
                  tail.setLocation(tail.getX()+xStep,tail.getY());
               } else {
                  //Move diagonally
                  if (dX == 1 && dY == 2){
                     int yStep = Double.valueOf(head.getY() - tail.getY()).intValue();
                     if (yStep < -1) yStep = -1;
                     if (yStep >  1) yStep = 1;
                     tail.setLocation(head.getX(),tail.getY()+yStep);
                  } else if (dX == 2 && dY == 1){
                     int xStep = Double.valueOf(head.getX() - tail.getX()).intValue();
                     if (xStep < -1) xStep = -1;
                     if (xStep >  1) xStep = 1;
                     tail.setLocation(tail.getX()+xStep,head.getY());
                  }
               }
            }
            visitedByTail.add(new Point(Double.valueOf(tail.getX()).intValue(), Double.valueOf(tail.getY()).intValue()));
         }
      }
      return String.valueOf(visitedByTail.size());
   }

   @Override
   public String processPart2() {
      Point[] rope = new Point[10];
      for (int i = 0; i < rope.length; i++){
         rope[i] = new Point(0,0);
      }
      /*for (String line : lines){
         String[] commands = line.split(" ");
         int steps = Integer.valueOf(commands[1]);
         for (int i = 0; i < steps; i++){
            if (commands[0].equals("R")){
               head.setLocation(head.getX()+1, head.getY());
            }else if (commands[0].equals("U")){
               head.setLocation(head.getX(), head.getY()-1);
            }else if (commands[0].equals("L")){
               head.setLocation(head.getX()-1, head.getY());
            }else if (commands[0].equals("D")){
               head.setLocation(head.getX(), head.getY()+1);
            }
            //System.out.println("H moved to " + head);
            //Compute distance between head and tail, and make follow
            int dY = Double.valueOf(Math.max(head.getY(), tail.getY()) - Math.min(head.getY(), tail.getY())).intValue();
            int dX = Double.valueOf(Math.max(head.getX(), tail.getX()) - Math.min(head.getX(), tail.getX())).intValue();
            if (dY > 1 || dX > 1){
               //Move tail
               if (dX == 0){ //Move Y
                  int yStep = Double.valueOf(head.getY() - tail.getY()).intValue();
                  if (yStep < -1) yStep = -1;
                  if (yStep >  1) yStep = 1;
                  tail.setLocation(tail.getX(), tail.getY() + yStep);
               }else if (dY == 0){ //Move X
                  int xStep = Double.valueOf(head.getX() - tail.getX()).intValue();
                  if (xStep < -1) xStep = -1;
                  if (xStep >  1) xStep = 1;
                  tail.setLocation(tail.getX()+xStep,tail.getY());
               } else {
                  //Move diagonally
                  if (dX == 1 && dY == 2){
                     int yStep = Double.valueOf(head.getY() - tail.getY()).intValue();
                     if (yStep < -1) yStep = -1;
                     if (yStep >  1) yStep = 1;
                     tail.setLocation(head.getX(),tail.getY()+yStep);
                  } else if (dX == 2 && dY == 1){
                     int xStep = Double.valueOf(head.getX() - tail.getX()).intValue();
                     if (xStep < -1) xStep = -1;
                     if (xStep >  1) xStep = 1;
                     tail.setLocation(tail.getX()+xStep,head.getY());
                  }


               }
              // System.out.println("T moved to " + tail);
            }
            visitedByTail.add(new Point(Double.valueOf(tail.getX()).intValue(), Double.valueOf(tail.getY()).intValue()));
         }
      }*/
      return String.valueOf(visitedByTail.size());
   }
}

