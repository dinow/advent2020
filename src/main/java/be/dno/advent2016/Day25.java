package be.dno.advent2016;

import java.util.HashMap;
import java.util.Map;

import be.dno.Day;

public class Day25 extends Day {

   private final Map<String, Long> registers = new HashMap<>();
   private String[] instructions;

   public Day25() {
      fileName = "2016/day25.txt";
   }

   public void fillDataStruct() {
      
      instructions = new String[lines.size()];
      for (int i = 0; i < lines.size(); i++) {
         instructions[i] = lines.get(i);
      }
   }

   @Override
   public String processPart1() {
      int val = 1;
      boolean myCondition = true;
      while(myCondition){
         registers.put("a", Long.valueOf(val));
         registers.put("b", Long.valueOf(0));
         registers.put("c", Long.valueOf(0));
         registers.put("d", Long.valueOf(0));
         myCondition = !processMachine();
         val++;
      }
      return "" + (val-1);
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


   private boolean processMachine() {
      long out1 = -1, out2 = -1, out3 = -1, out4 = -1;
      int i = 0;
      int counter = 0;
      while(i < instructions.length){
         counter++;
         if (counter > 1_000_000) return true;
         String line = instructions[i];
         try{
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
            } else if (instruction.equals("out")){
               Long val = registers.get(value);
               if (out1 == -1){ 
                  out1 = val;
               } else if (out2 == -1){
                  out2 = val;
               } else if (out3 == -1){
                  out3 = val;
               } else if (out4 == -1){
                  out4 = val;
               }
               if (out1 == 1) return false;
               if (out2 == 0) return false;
               if (out3 == 1) return false;
               if (out4 == 0) return false;

               if (out4 != -1){
                  out1 = -1;
                  out2 = -1;
                  out3 = -1;
                  out4 = -1;
               }
               i++;
            }else if (instruction.equals("cpy")){
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
      return false;
   }
}
