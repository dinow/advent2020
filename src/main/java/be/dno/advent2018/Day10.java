package be.dno.advent2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import be.dno.Day;
import be.dno.Utils;

public class Day10 extends Day{

   private Map<String, String> array = new HashMap<>();
   private List<Integer[]> instructions = new ArrayList<>();
   int maxX = Integer.MIN_VALUE;
   int minX = Integer.MAX_VALUE;
   int maxY = Integer.MIN_VALUE;
   int minY = Integer.MAX_VALUE;
   int idx = -1;
   public Day10(){
      fileName = "2018/day10.txt";
   }

   public void fillDataStruct(){
      for(String line : lines){
         String[] cleaned = line.replace("position=<", "").replace("> velocity=<", ",").replace(">", "").split(",");
         Integer[] data = new Integer[4];
         for (int i = 0; i < 4; i++){
            data[i] = Integer.valueOf(cleaned[i].trim());
         }
         instructions.add(data);
      }

      setBorders();
   }

   @Override
   public String processPart1() {
      int min_y = Integer.MAX_VALUE;
      int min_x = Integer.MAX_VALUE;
      
      List<Integer[]> origin = Utils.copy(instructions);

      for (int i = 1; i < 30_000; i++){
         array.clear();
         for(Integer[] arr : instructions){
            arr[0] += arr[2];
            arr[1] += arr[3];
            array.put(arr[1]+"_" + arr[0],"#");
         }
         setBorders();
         if ( ((minY*-1)+maxY) < min_y){
            min_y = ((minY*-1)+maxY);
            idx = i;
         }
         if ( ((minX*-1)+maxX) < min_x){
            min_x = ((minX*-1)+maxX);
            idx = i;
         }
      }

      instructions = Utils.copy(origin);
      for (int i = 1; i <= idx; i++){
         array.clear();
         for(Integer[] arr : instructions){
            arr[0] += arr[2];
            arr[1] += arr[3];
            array.put(arr[1]+"_" + arr[0],"#");
         }
         if (i == idx){
            setBorders();
            for (int y = minY; y <= maxY; y++){
               for (int x = minX; x <= maxX; x++){
                  if (array.containsKey(y+"_"+x)){
                     System.out.print("#");
                  } else {
                     System.out.print(" ");
                  }
               }
               System.out.print("\n");
            }
            System.out.print("\n");
         }
      }

      return "";
   }

   private void setBorders(){
      maxX = Integer.MIN_VALUE;
      minX = Integer.MAX_VALUE;
      maxY = Integer.MIN_VALUE;
      minY = Integer.MAX_VALUE;
      for(Integer[] data : instructions){
         if (data[0] > maxX) maxX = data[0];
         if (data[0] < minX) minX = data[0];

         if (data[1] > maxY) maxY = data[1];
         if (data[1] < minY) minY = data[1];
      }
   }

   @Override
   public String processPart2() {
      //idx = 3 seconds;
      return idx+"";
   }
}