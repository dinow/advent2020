package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

import be.dno.Day;

public class Day16 implements Day{

   private Map<String, int[]> fields = new HashMap<>();
   private Map<String, Integer> fieldsPosition = new HashMap<>();
   private int[] myTicket;
   private List<int[]> validTickets = new ArrayList<>();

   @Override
   public void run(String fileName) throws IOException {
      long startTime = System.nanoTime();

      fields.put("departure location", new int[]{ 41, 598, 605, 974});
      fields.put("departure station",  new int[]{ 30, 617, 625, 957});
      fields.put("departure platform", new int[]{ 29, 914, 931, 960});
      fields.put("departure track",    new int[]{ 39, 734, 756, 972});
      fields.put("departure date",     new int[]{ 37, 894, 915, 956});
      fields.put("departure time",     new int[]{ 48, 54 , 70 , 955});
      fields.put("arrival location",   new int[]{ 39, 469, 491, 955});
      fields.put("arrival station",    new int[]{ 47, 269, 282, 949});
      fields.put("arrival platform",   new int[]{ 26, 500, 521, 960});
      fields.put("arrival track",      new int[]{ 26, 681, 703, 953});
      fields.put("class",              new int[]{ 49, 293, 318, 956});
      fields.put("duration",           new int[]{ 25, 861, 873, 973});
      fields.put("price",              new int[]{ 30, 446, 465, 958});
      fields.put("route",              new int[]{ 50, 525, 551, 973});
      fields.put("row",                new int[]{ 39, 129, 141, 972});
      fields.put("seat",               new int[]{ 37, 566, 573, 953});
      fields.put("train",              new int[]{ 43, 330, 356, 969});
      fields.put("type",               new int[]{ 32, 770, 792, 955});
      fields.put("wagon",              new int[]{ 47, 435, 446, 961});
      fields.put("zone",               new int[]{ 30, 155, 179, 957});
      myTicket = Arrays.stream("71,127,181,179,113,109,79,151,97,107,53,193,73,83,191,101,89,149,103,197".split(",")).mapToInt(Integer::parseInt).toArray();

      System.out.println("Part 1 : " + processPart1(fileName));
      System.out.println("Part 2 : " + processPart2(validTickets.get(0).length));
      long endTime = System.nanoTime();
      long timeElapsed = endTime - startTime;
      System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);
   }

   public Integer processPart2(int nbFields) {
      int part2 = 1;
      //for each fields
      for(String field : fields.keySet()){
         int[] boundaries = fields.get(field);

         //for each ticket field position
         for (int i = 0; i < nbFields; i++){
            boolean allOk = true;
            //check all tickets, set allOk = false if one of the ticket does not match the boundaries
            for (int[] values : validTickets){
               if (!isValueOk(values[i], boundaries)){
                  allOk = false;
               }
            }
            //all tickets are valid for the field at that position
            if (allOk){
               System.out.println(field + " -> position " + i);
               if (field.startsWith("departure")){
                  part2 *= myTicket[i];
               }
               break;
            }
         }
      }
      return part2;
   }

   public boolean isValueOk(int value, int[] boundaries){
      return (value >= boundaries[0] && value <= boundaries[1]) || (value >= boundaries[2] && value <= boundaries[3]);
   }

   public Integer processPart1(String fileName) throws IOException{
      int part1 = 0;
      List<String> contents = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));
      for(String line : contents){
         //System.out.println(line);
         for (int value : Arrays.stream(line.split(",")).map(Integer::valueOf).collect(Collectors.toList())){
            boolean oneValid = false;
            boolean oneFalse = false;
            for (int[] boundaries : fields.values()){
               if (!isValueOk(value, boundaries)) oneFalse = true;
               if ( isValueOk(value, boundaries)){
                  oneValid = true;
                  break;
               }
            }
            if (!oneValid){
               part1+=value;
            }
            if (!oneFalse){
               validTickets.add(Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray());
            }
         }
      }
      return part1;
   }

}
