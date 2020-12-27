package be.dno.advent2016;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import be.dno.Day;
import be.dno.Utils;

public class Day10 extends Day{
   private Map<String, Integer[]> bots = new HashMap<>();
   private Map<String, Integer> outputBins = new HashMap<>();
   private String comparingBot = "";

   public Day10(){
      fileName = "2016/day10.txt";
   }

   @Override
   public String processPart1() {
      Set<String> actionsToPerform = new HashSet<>();
      actionsToPerform.addAll(lines);
      //give all initial values when possible
      for (String line : lines){
         if (line.startsWith("value ")){
            //System.out.println(line);
            int[] data = Utils.splitCouple(line.replace("value ", ""), " goes to bot ");
            giveValue(data[1]+"", data[0]);
            actionsToPerform.remove(line);
         }
      }
      
      while(!actionsToPerform.isEmpty()){
         for (String line : lines){
            if (!actionsToPerform.contains(line)) continue;
            if (line.startsWith("bot ")){ //bot 2 gives low to bot 1 and high to bot 0
               //System.out.println(line);
               String[] lowHigh = line.split("and"); //[0] -> bot 2 gives low to bot/output 1 //[1] -> high to bot/output 0
               Integer[] data = Utils.extractNumbers(line);
               if (!bots.containsKey(data[0]+"")) continue;
               Integer[] botData = bots.get(data[0]+"");
               if (botData[0].equals(Integer.MAX_VALUE) || botData[1].equals(Integer.MIN_VALUE)) continue;
               if (lowHigh[1].contains("output")){
                  //give high to output
                  outputBins.put(data[2]+"", botData[1]);
               } else {
                  //give high to bot
                  giveValue(data[2]+"", botData[1]);
               }
               if (lowHigh[0].contains("output")){
                  //give low to output
                  outputBins.put(data[1]+"", botData[0]);
               } else {
                  //give low to bot
                  giveValue(data[1]+"", botData[0]);
               }
               actionsToPerform.remove(line);
            }
         }
      }
      return comparingBot;
   }

   @Override
   public String processPart2() {
      return "" + (outputBins.get("0") * outputBins.get("1") * outputBins.get("2"));
   }

   private void giveValue(String botName, Integer value){

      if (!bots.containsKey(botName)){
         bots.put(botName, new Integer[]{Integer.MAX_VALUE, Integer.MIN_VALUE});
      }

      Integer[] values = bots.get(botName);
      if (values[0].equals(Integer.MAX_VALUE)){
         values[0] = value;
      } else {
         if (   (values[0].intValue() == 61 && value.intValue() == 17) 
             || (values[0].intValue() == 17 && value.intValue() == 61) ){
            comparingBot = botName;
         }
         if (values[0] > value){
            values[1] = values[0];
            values[0] = value;
         } else {
            values[1] = value;
         }
      }
      
   }
}
