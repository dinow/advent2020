package be.dno.advent2020;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import be.dno.Day;
public class Day15 implements Day{

   //int[0] -> last -1, int[1] -> last
   Map<Integer, int[]> memory = new HashMap<>();
   private static final Integer ZERO = Integer.valueOf(0);
   @Override
   public void run(String fileName) throws IOException {
      long startTime = System.nanoTime();
      System.out.println("Part 1 : " + processPart(fileName, 2020));
      System.out.println("Part 2 : " + processPart(fileName, 30000000));
      long endTime = System.nanoTime();
      long timeElapsed = endTime - startTime;
      System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);
   }

   public Integer processPart(String fileName, int maxValue){
      memory.clear();
      Integer lastSpoken = null;
      int counter = 1;
      Integer number;
      for(String input : fileName.split(",")){
         number = Integer.valueOf(input);
         lastSpoken = number;
         memory.put(lastSpoken, new int[]{-1, counter});
         counter ++;
      }
      int[] arr;
      while (counter <= maxValue){
         if (!memory.containsKey(lastSpoken) || memory.get(lastSpoken)[0] == -1){
            lastSpoken = ZERO;
         } else {
            arr = memory.get(lastSpoken);
            lastSpoken = arr[1] - arr[0];
         }
         addNumber(lastSpoken, counter);
         counter++;
      }
      return lastSpoken;
   }

   public void addNumber(Integer number, int position){
      if (!memory.containsKey(number)){
         memory.put(number, new int[]{-1, position});
      } else {
         int[] arr = memory.get(number);
         arr[0] = memory.get(number)[1];
         arr[1] = position;
         memory.put(number, arr);
      }
   }
}
