package be.dno.advent2020;

import java.io.IOException;
import java.nio.charset.Charset;
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
   private Map<String, int[]> validTickets = new HashMap<>();
   private List<String> contents;

   @Override
	public void fillDataStruct(String fileName) throws IOException {
      fields.clear();
      contents = IOUtils.readLines(ClassLoader.getSystemResourceAsStream(fileName), Charset.forName("UTF-8"));
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
	}

	@Override
	public String processPart1() {
		return "" + _processPart1();
	}

	@Override
	public String processPart2() {
		return "" + _processPart2(myTicket.length);
   }

   public boolean isOneRuleOk(int number){
      for (int[] boundaries : fields.values()){
         if (isValueOk(number, boundaries)) return true;
      }
      return false;
   }

   public long _processPart2(int nbFields) {
      long part2 = 1l;

      for(String line : contents){
         boolean allOk = true;
         for (int value : Arrays.stream(line.split(",")).map(Integer::valueOf).collect(Collectors.toList())){
            allOk &= isOneRuleOk(value);
         }
         if (allOk){
            if (!validTickets.containsKey(line)) {
               validTickets.put(line, Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray());
            }
         }
      }

      HashSet<String> okRules = new HashSet<>();
      while(!fields.isEmpty()){
         for (int i = 0; i < nbFields; i++){
            okRules.clear();
            okRules.addAll(fields.keySet());
            for (int[] ticket : validTickets.values()){
               for(String field : fields.keySet()){
                  if(!isValueOk(ticket[i], fields.get(field))){
                     okRules.remove(field);
                  }
               }
            }
            if (okRules.size() == 1){
               String field = okRules.iterator().next();
               if (field.startsWith("departure")){
                  part2 *= myTicket[i];
               }
               fieldsPosition.put(field, i);
               fields.remove(field);
            }
         }
      }
      return part2;
   }

   public boolean isValueOk(int value, int[] boundaries){
      return (value >= boundaries[0] && value <= boundaries[1]) || (value >= boundaries[2] && value <= boundaries[3]);
   }

   public Integer _processPart1(){
      int part1 = 0;
      for(String line : contents){
         for (int value : Arrays.stream(line.split(",")).map(Integer::valueOf).collect(Collectors.toList())){
            boolean oneValid = false;
            for (int[] boundaries : fields.values()){
               if ( isValueOk(value, boundaries)) oneValid = true;
            }
            if (!oneValid){
               part1+=value;
            }
         }
      }
      return part1;
   }

}
