package be.dno.advent2022;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Map.Entry;

import be.dno.PointLong;

import be.dno.Day;
import be.dno.Point;
import be.dno.Point3d;
import be.dno.Utils;


public class Day18 extends Day {
   int MIN_X;
   int MAX_X;
   int MIN_Y;
   int MAX_Y;
   int MIN_Z;
   int MAX_Z;
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
      for (Point3d e : elements){
         MIN_X = Math.min(MIN_X, e.getX());
         MIN_Y = Math.min(MIN_Y, e.getY());
         MIN_Z = Math.min(MIN_Z, e.getZ());

         MAX_X = Math.max(MAX_X, e.getX());
         MAX_Y = Math.max(MAX_Y, e.getY());
         MAX_Z = Math.max(MAX_Z, e.getZ());
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

   private int minDistance(int x, int y, int z){
      Point3d source = new Point3d(x, y, z, 0);

      // applying BFS on matrix cells starting from source
      Queue<Point3d> queue = new LinkedList<>();
      queue.add(new Point3d(source.getX(), source.getY(), source.getZ(), 0));

      Map<Point3d, Boolean> visited = new HashMap<>();
      visited.put(new Point3d(source.getX(), source.getY(), source.getZ()),Boolean.TRUE);

      while (queue.isEmpty() == false) {
         Point3d p = queue.remove();

         // Destination found;
         if ((p.getX() > MAX_X || p.getX() < MIN_X) && 
             (p.getY() > MAX_Y || p.getY() < MIN_Y) &&
             (p.getZ() > MAX_Z || p.getZ() < MIN_Z)){
            return p.getDistance();
         }

         // moving up
         if (            isValid(p, p.getX() - 1, p.getY(), p.getZ(), visited)) {
            queue.add(  new Point3d(p.getX() - 1, p.getY(), p.getZ(), p.getDistance() + 1));
            visited.put(new Point3d(p.getX() - 1, p.getY(), source.getZ()), Boolean.TRUE);
         }

         // moving down
         if (            isValid(p, p.getX() + 1, p.getY(), p.getZ(), visited)) {
            queue.add(  new Point3d(p.getX() + 1, p.getY(), p.getZ(), p.getDistance() + 1));
            visited.put(new Point3d(p.getX() + 1, p.getY(), source.getZ()),Boolean.TRUE);
         }

         // moving left
         if (           isValid(p, p.getX(), p.getY() - 1, p.getZ(), visited)) {
           queue.add(  new Point3d(p.getX(), p.getY() - 1, p.getZ(), p.getDistance() + 1));
           visited.put(new Point3d(p.getX(), p.getY() - 1, source.getZ()),Boolean.TRUE);
         }

         // moving right
         if (           isValid(p, p.getX(), p.getY() + 1, p.getZ(), visited)) {
           queue.add(  new Point3d(p.getX(), p.getY() + 1, p.getZ(), p.getDistance() + 1));
           visited.put(new Point3d(p.getX(), p.getY() + 1, source.getZ()),Boolean.TRUE);
         }

         //moving forward
         if (isValid(p,             p.getX(), p.getY(), p.getZ()-1, visited)) {
            queue.add(  new Point3d(p.getX(), p.getY(), p.getZ()-1, p.getDistance() + 1));
            visited.put(new Point3d(p.getX(), p.getY(), source.getZ()-1),Boolean.TRUE);
          }

         //moving backward
         if (isValid(p,             p.getX(), p.getY(), p.getZ()+1, visited)) {
            queue.add(  new Point3d(p.getX(), p.getY(), p.getZ()+1, p.getDistance() + 1));
            visited.put(new Point3d(p.getX(), p.getY(), source.getZ()+1),Boolean.TRUE);
          }
      }
      return -1;
   }

   private boolean isValid(Point3d p, int x, int y, int z, Map<Point3d, Boolean> visited) {
      if (!elements.contains(p) && !visited.containsKey(new Point3d(x, y, z))){
         return true;
      }
      return false;
   }
   

   private long countNotTouching(Point3d cube, boolean onlyOutside) {
      int notTouching = 6;
      for (Point3d other : elements){
         if (other.equals(cube)) continue;
         //if cube on top
         if (other.getY() == cube.getY()+1 && other.getX() == cube.getX() && other.getZ() == cube.getZ()){
            notTouching--;
            continue;
         } else if (onlyOutside){
            int distanceToOutside = minDistance(other.getX(), other.getY(), other.getZ());
            if (distanceToOutside == -1){
               notTouching--;
            }
         }

         //if cube below
         if (other.getY() == cube.getY()-1 && other.getX() == cube.getX() && other.getZ() == cube.getZ()){
            notTouching--;
            continue;
         } else if (onlyOutside){
            int distanceToOutside = minDistance(other.getX(), other.getY(), other.getZ());
            if (distanceToOutside == -1){
               notTouching--;
            }
         }

         //if cube front
         if (other.getY() == cube.getY() && other.getX() == cube.getX() && other.getZ() == cube.getZ()+1){
            notTouching--;
            continue;
         } else if (onlyOutside){
            int distanceToOutside = minDistance(other.getX(), other.getY(), other.getZ());
            if (distanceToOutside == -1){
               notTouching--;
            }
         }

         //if cube behind
         if (other.getY() == cube.getY() && other.getX() == cube.getX() && other.getZ() == cube.getZ()-1){
            notTouching--;
            continue;
         } else if (onlyOutside){
            int distanceToOutside = minDistance(other.getX(), other.getY(), other.getZ());
            if (distanceToOutside == -1){
               notTouching--;
            }
         }

         //if left
         if (other.getY() == cube.getY() && other.getX() == cube.getX()+1 && other.getZ() == cube.getZ()){
            notTouching--;
            continue;
         } else if (onlyOutside){
            int distanceToOutside = minDistance(other.getX(), other.getY(), other.getZ());
            if (distanceToOutside == -1){
               notTouching--;
            }
         }

         //if right
         if (other.getY() == cube.getY() && other.getX() == cube.getX()-1 && other.getZ() == cube.getZ()){
            notTouching--;
            continue;
         } else if (onlyOutside){
            int distanceToOutside = minDistance(other.getX(), other.getY(), other.getZ());
            if (distanceToOutside == -1){
               notTouching--;
            }
         }

         if (notTouching == 0) return 0;
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

