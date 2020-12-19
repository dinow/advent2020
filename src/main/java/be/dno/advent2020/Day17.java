package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import be.dno.Day;

public class Day17 implements Day{
   private final Set<Day17Point> activesValues = new HashSet<>();
   private int cube_border_size;

   @Override
   public void run(String fileName) throws IOException {
      long startTime = System.nanoTime();
      fillMap(fileName);
      System.out.println("Part 1 : " + processPart1(6));
      activesValues.clear();
      fillMap(fileName);
      System.out.println("Part 2 : " + processPart2(6));
      long endTime = System.nanoTime();
      long timeElapsed = endTime - startTime;
      System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);
   }

   public long processPart1(int cycles) {
      for (int cycle = 0; cycle < cycles; cycle++){
         Set<Day17Point> toAdd = new HashSet<>();
         Set<Day17Point> toRemove = new HashSet<>();
         toAdd = getPointsToAdd(cycle);
         for(Day17Point point : activesValues.toArray(new Day17Point[]{})){
            int activeNeighbors   = countActiveNeighbors(point,cycle);
            //If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active. 
            //Otherwise, the cube becomes inactive.
            if (activeNeighbors != 2 && activeNeighbors != 3){
               toRemove.add(point);
            }
         }
         activesValues.removeAll(toRemove);
         activesValues.addAll(toAdd);
      }
      return activesValues.size();
   }

   public long processPart2(int cycles) {
      for (int cycle = 0; cycle < cycles; cycle++){
         Set<Day17Point> toAdd = new HashSet<>();
         Set<Day17Point> toRemove = new HashSet<>();
         toAdd = getPointsToAdd2(cycle);
         for(Day17Point point : activesValues.toArray(new Day17Point[]{})){
            int activeNeighbors  = countActiveNeighbors2(point,cycle);
            if (activeNeighbors != 2 && activeNeighbors != 3){
               toRemove.add(point);
            }
         }
         activesValues.removeAll(toRemove);
         activesValues.addAll(toAdd);
      }
      return activesValues.size();
   }

   public int countActiveNeighbors(Day17Point position, int cycle){
      int actives = 0;
      for (int z = position.z-1; z <= position.z+1; z++){
         for (int x = position.x-1; x <= position.x+1; x++){
            for (int y = position.y-1; y <= position.y+1; y++){
               if (!(position.x == x && position.y == y && position.z == z)){
                  if (activesValues.contains(new Day17Point(x, y, z, 0))){
                     actives++;
                  }
               }
            }
         }
      }
      return actives;
   }

   public int countActiveNeighbors2(Day17Point position, int cycle){
      int actives = 0;
      for (int z = position.z-1; z <= position.z+1; z++){
         for (int x = position.x-1; x <= position.x+1; x++){
            for (int y = position.y-1; y <= position.y+1; y++){
               for (int w = position.w-1; w <= position.w+1; w++){
                  if (!(position.x == x && position.y == y && position.z == z && position.w == w)){
                     if (activesValues.contains(new Day17Point(x, y, z, w))){
                        actives++;
                     }
                  }
               }
            }
         }
      }
      return actives;
   }

   /**
    * Return the all the points that are in the "center" of this points with exactly 3 active points (one is the position)
    * @param position
    * @return
    */
   public Set<Day17Point> getPointsToAdd(int cycle){
      Set<Day17Point> points = new HashSet<>();
      //iterate trhough the virtual array
      for (int z = (0-(cycle+1)); z <= (cube_border_size+cycle); z++){
         for (int x = (0-(cycle+1)); x <= (cube_border_size+cycle); x++){
            for (int y = (0-(cycle+1)); y <= (cube_border_size+cycle); y++){
               Day17Point point = new Day17Point(x, y, z, 0);
               int activeNeighbors = countActiveNeighbors(point, cycle);
               if (activeNeighbors == 3){
                  points.add(point);
               }
            }
         }
      }
      return points;
   }

   public Set<Day17Point> getPointsToAdd2(int cycle){
      Set<Day17Point> points = new HashSet<>();
      //iterate trhough the virtual array
      for (int z = (0-(cycle+1)); z <= (cube_border_size+cycle); z++){
         for (int x = (0-(cycle+1)); x <= (cube_border_size+cycle); x++){
            for (int y = (0-(cycle+1)); y <= (cube_border_size+cycle); y++){
               for (int w = (0-(cycle+1)); w <= (cube_border_size+cycle); w++){
                  Day17Point point = new Day17Point(x, y, z, w);
                  int activeNeighbors = countActiveNeighbors2(point, cycle);
                  if (activeNeighbors == 3){
                     points.add(point);
                  }
               }
            }
         }
      }
      return points;
   }

   public void fillMap(String fileName) throws IOException{
      List<String> contents = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));
      cube_border_size = contents.size();

      for (int x = 0; x < cube_border_size; x++) {
         for (int y = 0; y < cube_border_size; y++){
            if (contents.get(x).charAt(y) == '#'){
               activesValues.add(new Day17Point(x,y,0, 0));
            }
         }
      }
   }

}
