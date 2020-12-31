package be.dno.advent2016;

import java.util.HashMap;
import java.util.Map;

import be.dno.Day;

public class Day12 extends Day{

   private final Map<String, Integer> registers = new HashMap<>();
   private String[] instructions;

   public Day12(){
      fileName = "2016/day12.txt";
   }

   public void fillDataStruct(){
      registers.put("a", Integer.valueOf(0));
      registers.put("b", Integer.valueOf(0));
      registers.put("c", Integer.valueOf(0));
      registers.put("d", Integer.valueOf(0));
      instructions = new String[lines.size()];
      for(int i = 0; i < lines.size(); i++){
         instructions[i] = lines.get(i);
      }
   }

   @Override
   public String processPart1() {
      processMachine();
      return ""+registers.get("a");
   }

   private Integer getVal(String value){
      Integer val;
      if (value.equals("a") || value.equals("b") ||value.equals("c") || value.equals("d")){
         val = registers.get(value);
      }else{
         val = Integer.valueOf(value);
      }
      return val;
   }

   @Override
   public String processPart2() {
      registers.put("a", Integer.valueOf(0));
      registers.put("b", Integer.valueOf(0));
      registers.put("c", Integer.valueOf(1));
      registers.put("d", Integer.valueOf(0));
      processMachine();
      return ""+registers.get("a");
   }

   private void processMachine(){
      int i = 0;
      while(i < instructions.length){
         String line = instructions[i];
         String instruction = line.substring(0, 3);
         String value = line.substring(4);
         if (instruction.equals("inc")){
            Integer val = registers.get(value);
            val++;
            registers.put(value, val);
            i++;
         } else if (instruction.equals("dec")){
            Integer val = registers.get(value);
            val--;
            registers.put(value, val);
            i++;
         } else if (instruction.equals("cpy")){
            String[] values = value.split(" ");
            Integer val = getVal(values[0]);
            registers.put(values[1], val);
            i++;
         } else if (instruction.equals("jnz")){
            String[] values = value.split(" ");
            Integer val = getVal(values[0]);
            if (val.intValue() != 0){
               i+= Integer.valueOf(values[1]);
            } else {
               i++;
            }
         }
      }
   }
}
