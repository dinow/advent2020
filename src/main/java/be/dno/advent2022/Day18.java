package be.dno.advent2022;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import be.dno.PointLong;

import be.dno.Day;
import be.dno.Point;
import be.dno.Point3d;
import be.dno.Utils;


public class Day18 extends Day {

   private Set<Point3d> elements;

   public Day18(){
      fileName = "2022/day18.txt";
   }

   @Override
   public void fillDataStruct() {
      elements = new HashSet<>();
      for (String line : lines){
         List<Integer> coords = Utils.extractNumbersList(line);
         elements.add(new Point3d(coords.get(0), coords.get(1), coords.get(2)));
      }

   }

   @Override
   public String processPart1() throws Exception {
      long surfaceArea = 0l;
      for (Point3d cube : elements){
         surfaceArea += countNotTouching(cube, false);
      }
      return String.valueOf(surfaceArea);
   }

   

   private long countNotTouching(Point3d cube, boolean onlyOutside) {
      int notTouching = 6;
      for (Point3d other : elements){
         if (other.equals(cube)) continue;
         //if cube on top
         if (other.getY() == cube.getY()+1 && other.getX() == cube.getX() && other.getZ() == cube.getZ()){
            notTouching--;
            continue;
         }
         //if cube below
         if (other.getY() == cube.getY()-1 && other.getX() == cube.getX() && other.getZ() == cube.getZ()){
            notTouching--;
            continue;
         }

         //if cube front
         if (other.getY() == cube.getY() && other.getX() == cube.getX() && other.getZ() == cube.getZ()+1){
            notTouching--;
            continue;
         }

         //if cube behind
         if (other.getY() == cube.getY() && other.getX() == cube.getX() && other.getZ() == cube.getZ()-1){
            notTouching--;
            continue;
         }

         //if left
         if (other.getY() == cube.getY() && other.getX() == cube.getX()+1 && other.getZ() == cube.getZ()){
            notTouching--;
            continue;
         }

         //if right
         if (other.getY() == cube.getY() && other.getX() == cube.getX()-1 && other.getZ() == cube.getZ()){
            notTouching--;
            continue;
         }

         if (notTouching == 0) return 0;
      }
      if (onlyOutside){
         //count the sides that are exposed
         //??
      }
      return notTouching;
   }

   @Override
   public String processPart2() throws Exception {
      long surfaceArea = 0l;
      for (Point3d cube : elements){
         surfaceArea += countNotTouching(cube, true);
      }
      return String.valueOf(surfaceArea);
   }
}

