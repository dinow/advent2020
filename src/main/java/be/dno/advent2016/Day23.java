package be.dno.advent2016;

import java.util.HashMap;
import java.util.Map;

import be.dno.Day;

public class Day23 extends Day {

   private final Map<String, Long> registers = new HashMap<>();
   private String[] instructions;

   public Day23() {
      fileName = "2016/day23.txt";
   }

   public void fillDataStruct() {
      registers.put("a", Long.valueOf(7));
      registers.put("b", Long.valueOf(0));
      registers.put("c", Long.valueOf(0));
      registers.put("d", Long.valueOf(0));
      instructions = new String[lines.size()];
      for (int i = 0; i < lines.size(); i++) {
         instructions[i] = lines.get(i);
      }
   }

   @Override
   public String processPart1() {
      processMachine();
      return "" + registers.get("a");
   }

   private Long getVal(String value) {
      Long val;
      if (value.equals("a") || value.equals("b") || value.equals("c") || value.equals("d")) {
         val = registers.get(value);
      } else {
         val = Long.valueOf(value);
      }
      return val;
   }

   @Override
   public String processPart2() {
      registers.put("a", Long.valueOf(12));
      processMachine();
      return ""+registers.get("a");
   }

   private void processMachine() {
      int i = 0;
      while(i < instructions.length){
         String line = instructions[i];
         try{
            //System.out.println(line);
            String instruction = line.substring(0, 3);
            String value = line.substring(4);
            if (instruction.equals("inc")){
               Long val = registers.get(value);
               val++;
               registers.put(value, val);
               i++;
            } else if (instruction.equals("dec")){
               Long val = registers.get(value);
               val--;
               registers.put(value, val);
               i++;
            } else if (instruction.equals("cpy")){
               String[] values = value.split(" ");
               Long val = getVal(values[0]);
               if (!registers.containsKey(values[1])) throw new Exception("Wrong cpy");
               registers.put(values[1], val);
               i++;
            } else if (instruction.equals("jnz")){
               String[] values = value.split(" ");
               Long val = getVal(values[0]);
               if (val.intValue() != 0){
                  i+= getVal(values[1]);
               } else {
                  i++;
               }
            } else if (instruction.equals("tgl")){
               String[] values = value.split(" ");
               Long val = i+getVal(values[0]);
               if (val >= 0 && val < instructions.length){
                  String instrToToggle = instructions[val.intValue()].substring(0, 3);
                  if (instrToToggle.equals("inc")){
                     instrToToggle = "dec";
                  } else if (instrToToggle.equals("dec")){
                     instrToToggle = "inc";
                  } else if (instrToToggle.equals("tgl")){
                     instrToToggle = "inc";
                  } else if (instrToToggle.equals("jnz")){
                     instrToToggle = "cpy";
                  } else if (instrToToggle.equals("cpy")){
                     instrToToggle = "jnz";
                  } else {
                     throw new Exception ("unknown op " + line);
                  }
                  String old = instructions[val.intValue()];
                  instructions[val.intValue()] = instrToToggle + " " + instructions[val.intValue()].substring(4);
                  System.out.println("Switched ["+old+"] to ["+instructions[val.intValue()]+"]");
               }
               i++;
            }
         }catch(Exception ex){
            System.out.println("Wrong instruction : "+line);
            i++;
         }
      }
   }
}
