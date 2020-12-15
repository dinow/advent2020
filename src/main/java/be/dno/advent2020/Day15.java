package be.dno.advent2020;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day15 implements Day{

   Map<Integer, Map<String, Integer>> memory = new HashMap<>();

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
         memory.put(lastSpoken, new HashMap<>());
         memory.get(lastSpoken).put("LAST", Integer.valueOf(counter));
         counter ++;
      }
      while (counter <= maxValue){
         if (!memory.containsKey(lastSpoken) || !memory.get(lastSpoken).containsKey("LAST-1")){
            lastSpoken = Integer.valueOf(0);
         } else {
            lastSpoken = memory.get(lastSpoken).get("LAST") - memory.get(lastSpoken).get("LAST-1");
         }
         addNumber(lastSpoken, counter);
         counter++;
      }
      return lastSpoken;
   }

   public void addNumber(Integer number, int position){
      if (!memory.containsKey(number)){
         memory.put(number, new HashMap<>());
         memory.get(number).put("LAST", Integer.valueOf(position));
      } else {
         memory.get(number).put("LAST-1", memory.get(number).get("LAST"));
         memory.get(number).put("LAST", Integer.valueOf(position));
      }
   }
}
