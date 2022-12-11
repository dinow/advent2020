package be.dno.advent2022;

import java.awt.Point;
import java.security.spec.ECFieldF2m;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import be.dno.Day;


public class Day10 extends Day {
   private int registerX;
   private List<Integer> values;
   public Day10(){
      fileName = "2022/day10.txt";
   }

   @Override
   public void fillDataStruct() {
      registerX =1;
      values = new ArrayList<>();
   }

   private void processLine(String line){
      if (line.equals("noop")){
         values.add(registerX);

      }else{
         int value = Integer.valueOf(line.split(" ")[1]);
      
         values.add(registerX);

         values.add(registerX);

         registerX += value;
      }
   }

   private void draw(){
      int cycle = 0;
      for (int row = 0; row < 6; row++){
         for (int col = 0; col < 40; col++){
            cycle++;
            System.out.print("#");
         }
         System.out.print("\n");
      }
   }


   @Override
   public String processPart1() throws Exception {
      for (String line : lines){
         processLine(line);
      }
      long signalStrength = 0l;
      for (int i = 20; i <= 220; i+= 40){
         signalStrength += i  * values.get(i-1);
      }
      return String.valueOf(signalStrength);
   }

   @Override
   public String processPart2() throws Exception {
      for (String line : lines){
         processLine(line);
      }
      for(int y=0; y < 6; y++) {
         for(int x = 0; x < 40; x++) {
             int signalAt = values.get(y*40 + x);
             if(x == signalAt || x == signalAt - 1 || x == signalAt + 1){
                 System.out.print("#");
             }else{
                 System.out.print(" ");
             }
         }
         System.out.println();
     }
      return String.valueOf("");
   }
}

