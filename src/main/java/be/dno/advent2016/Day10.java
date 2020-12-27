package be.dno.advent2016;


import java.util.HashMap;
import java.util.Map;


import be.dno.Day;
import be.dno.Utils;

public class Day10 extends Day{
   private Map<String, int[]> bots = new HashMap<>();
   private String comparingBot = "";


   @Override
   public String processPart1() {
      for (String line : lines){
         if (line.startsWith("value ")){
            int[] data = Utils.splitCouple(line.replace("value ", ""), " goes to bot ");
            if (!bots.containsKey(data[1]+"")){
               bots.put(data[1]+"", new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE});
            }
            giveValue(data[1]+"", data[0]);
         }
      }
      for (String line : lines){
         if (line.startsWith("bot ")){
            Integer[] data = Utils.extractNumbers(line);
            int[] botData = bots.get(data[0]+"");
            //to bot or to output !
         }
      }
      return comparingBot;
   }

   @Override
   public String processPart2() {
      return "";
   }

   private void giveValue(String botName, int value){
      int[] values = bots.get(botName);
      if (values[0] == Integer.MAX_VALUE ){
         values[0] = value;
      } else {
         if ((values[0] == 61 && value == 17) || (values[0] == 17 && value == 61) ){
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
