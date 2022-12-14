package be.dno.advent2022;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import be.dno.Point;

import be.dno.Day;
import be.dno.Utils;


public class Day14 extends Day {

   private Set<Point> cave;
   private int maxY;

   public Day14(){
      fileName = "2022/day14.txt";
   }

   @Override
   public void fillDataStruct() {
      cave = new HashSet<>();
      for (String line : lines){
         Point prevCoord = null;
         for(String couple : line.split("->")){
            List<Integer> coords = Utils.extractNumbersList(couple);
            Point coord = new Point(coords.get(0), coords.get(1));
            if (prevCoord == null){
               prevCoord = new Point(coord.getX(), coord.getY());
            }
            //draw from prevCoord to coord (inclusive)
            fillCaveLine(prevCoord, coord);
            prevCoord = coord;
         }
      }
      maxY = Integer.MIN_VALUE;
      for(Point c : cave){
         int curY = c.getY();
         if (curY > maxY) maxY = curY;
      }
      maxY+=2;
   }

   private void fillCaveLine(Point prevCoord, Point coord) {
      int startX = Math.min(prevCoord.getX(), coord.getX());
      int endX   = Math.max(prevCoord.getX(), coord.getX());

      int startY = Math.min(prevCoord.getY(), coord.getY());
      int endY   = Math.max(prevCoord.getY(), coord.getY());
      for (int x = startX; x <= endX; x++){
         for (int y = startY; y <= endY; y++){
            cave.add(new Point(x, y));
         }
      }
   }

   @Override
   public String processPart1() throws Exception {
      boolean isFreeFall = false;
      int sandCounter = 0;
      while(!isFreeFall){
         Point sand = new Point(500, 0);
         boolean isResting = false;
         sandCounter++;
         while (!isFreeFall && !isResting){
            if (nothingBelow(sand)){
               isFreeFall = true;
               isResting = true;
               sandCounter--;
               break;
            }
            int oldX = sand.getX();
            int oldY = sand.getY();
            
            moveSand(sand);
            if (sand.getX() == oldX && sand.getY() == oldY){
               isResting = true;
               cave.add(sand);
            }
         }
      }
      return String.valueOf(sandCounter);
   }

   private boolean caveContain(int x, int y){
      return cave.contains(new Point(x, y));
   }

   private boolean nothingBelow(Point sand) {
      return sand.getY() >= maxY;
   }

   private void moveSand(Point sand){
      if (!caveContain(sand.getX(),sand.getY()+1)){
         sand.setY(sand.getY()+1);
      }else{
         if (!caveContain(sand.getX()-1,sand.getY()+1)){
            sand.setY(sand.getY()+1);
            sand.setX(sand.getX()-1);
         } else {
            if (!caveContain(sand.getX()+1,sand.getY()+1)){
               sand.setY(sand.getY()+1);
               sand.setX(sand.getX()+1);
            }
         }
      }
   }

   @Override
   public String processPart2() throws Exception {
      int sandCounter = 0;
      while(true){
         if (caveContain(500,0)){
            break;
         }
         Point sand = new Point(500, 0);
         boolean isResting = false;
         sandCounter++;
         
         while (!isResting){
            int oldX = sand.getX();
            int oldY = sand.getY();
            
            moveSand(sand);
            if ( (sand.getX() == oldX && sand.getY() == oldY) || sand.getY() == maxY-1){
               isResting = true;
               cave.add(sand);
            }
         }

      }
      return String.valueOf(sandCounter);
   }
}

