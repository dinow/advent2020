package be.dno.advent2022;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import be.dno.Day;


public class Day11 extends Day {

   public class Monkey {
      private ArrayDeque<Long> items;
      private String operation;
      private String operationValue;
      private int test;
      private String monkeyTestTrue;
      private String monkeyTestFalse;
      private int inspectedItems;
   
      
   
      public Monkey(String operation, String operationValue, int test, String monkeyTestTrue, String monkeyTestFalse, String fullItems) {
         this.operation = operation;
         this.operationValue = operationValue;
         this.test = test;
         this.monkeyTestTrue = monkeyTestTrue;
         this.monkeyTestFalse = monkeyTestFalse;
         this.items = new ArrayDeque<>();
         this.inspectedItems = 0;
         for (String item : fullItems.split(",")){
            this.items.add(Long.valueOf(item));
         }
      }

      public void play(boolean divide) {
         while (!this.items.isEmpty()){
            Long item = this.items.removeFirst();
            this.inspectedItems++;
            long opValue = 0l;
         
            if (this.operationValue.equals("old")){
               opValue = item;
            }else{
               opValue = Long.valueOf(this.operationValue);
            }

            if (this.operation.equals("*")){
               item *= opValue;
            }else{
               item += opValue;
            }
            
            if (divide){
               item /= 3;
            }

            if (item % this.test == 0){
               monkeys.get(this.monkeyTestTrue).receive(item);
            }else{
               monkeys.get(this.monkeyTestFalse).receive(item);
            }
         }
      }

      private void receive(Long item) {
         this.items.add(item % wtfSuperModulo);
      }

   }

   Map<String, Monkey> monkeys;
   Long wtfSuperModulo;

   public Day11(){
      fileName = "2022/day11.txt";
   }

   @Override
   public void fillDataStruct() {
      monkeys = new HashMap<>();
      if (lines.get(0).equals("test")){
         monkeys.put("0", new Monkey("*", "19" , 23, "2", "3", "79,98"));
         monkeys.put("1", new Monkey("+", "6"  , 19, "2", "0", "54,65,75,74"));
         monkeys.put("2", new Monkey("*", "old", 13, "1", "3","79,60,97"));
         monkeys.put("3", new Monkey("+", "3"  , 17, "0", "1","74"));
         wtfSuperModulo = 23l*19l*13l*17l;
      } else {
         monkeys.put("0", new Monkey("*", "7"  , 19, "6", "7", "85,77,77"));
         monkeys.put("1", new Monkey("*", "11" , 3 , "3", "5", "80,99"));
         monkeys.put("2", new Monkey("+", "8"  , 13, "0", "6","74,60,74,63,86,92,80"));
         monkeys.put("3", new Monkey("+", "7"  , 7 , "2", "4","71,58,93,65,80,68,54,71"));
         monkeys.put("4", new Monkey("+", "5"  , 5 , "2", "0","97,56,79,65,58"));
         monkeys.put("5", new Monkey("+", "4"  , 11, "4", "3","77"));
         monkeys.put("6", new Monkey("*", "old", 17, "7", "1","99,90,84,50"));
         monkeys.put("7", new Monkey("+", "3"  , 2 , "5", "1","50,66,61,92,64,78"));
         wtfSuperModulo = 19l*3l*13l*7l*7l*5l*11l*17l*2l;
      }
      
   }

   private long getAnswer(){
      List<Monkey> sortedByInspected = new ArrayList<>();
      for (int m = 0; m < monkeys.size(); m++){
         sortedByInspected.add(monkeys.get(String.valueOf(m)));
      }
      Collections.sort(sortedByInspected, new Comparator<Monkey>() {
         @Override
         public int compare(Monkey o1, Monkey o2) {
            return o2.inspectedItems - o1.inspectedItems;
         }
      });
      long monkeyBusiness1 = (sortedByInspected.get(0).inspectedItems);
      long monkeyBusiness2 = (sortedByInspected.get(1).inspectedItems);
      return monkeyBusiness1 * monkeyBusiness2;
   }

   @Override
   public String processPart1() throws Exception {
      for (int i = 0; i < 20; i++){
         for (int m = 0; m < monkeys.size(); m++){
            monkeys.get(String.valueOf(m)).play(true);
         }
      }
      return String.valueOf(getAnswer());
   }

   @Override
   public String processPart2() throws Exception {
      for (int i = 0; i < 10_000; i++){
         for (int m = 0; m < monkeys.size(); m++){
            monkeys.get(String.valueOf(m)).play(false);
         }
      }
      return String.valueOf(getAnswer());
   }
}

