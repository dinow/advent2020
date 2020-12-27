package be.dno.advent2015;

import java.util.HashMap;
import java.util.Map;

import be.dno.Day;

public class Day25 extends Day {
   private int targetRow;
   private int targetColumn;

   @Override
   public void fillDataStruct() {
      String input = lines.get(0);
      targetRow = Integer.valueOf(input.split(";")[0]);
      targetColumn = Integer.valueOf(input.split(";")[1]);
   }

   @Override
   public String processPart1() {
      String targetKey = targetRow+";"+targetColumn;
      int cur_row = 1;
      Map<String, Long> manual = new HashMap<>();
      manual.put("1;1", 20151125l);
      Long prevValue = 20151125l;
      while (!manual.containsKey(targetKey)){
         cur_row++;
         for (int cur_col = 1; cur_col <= cur_row; cur_col++){
            prevValue *= 252533l;
            prevValue %= 33554393l;
            manual.put((cur_row-(cur_col-1))+";"+cur_col, prevValue);
         }
         
      }
      return ""+manual.get(targetKey);
   }

   @Override
   public String processPart2() {
      return "";
   }
}
