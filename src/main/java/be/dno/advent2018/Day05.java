package be.dno.advent2018;

import java.util.ArrayDeque;
import java.util.Deque;

import be.dno.Day;

public class Day05 extends Day{
   String line;
   Deque<String> myStack = new ArrayDeque<>();
   String toRemove = null;
   
   public Day05(){
      fileName = "2018/day05.txt";
   }

   public void fillDataStruct(){
      myStack = new ArrayDeque<>();
      line = lines.get(0);
   }

   private boolean isOpposite(String a, String b){
      return !a.equals(b) && a.equalsIgnoreCase(b);
   }

   @Override
   public String processPart1() {
      for (int i = 0; i < line.length(); i++){
         if (!(line.charAt(i)+"").equalsIgnoreCase(toRemove)){
            if ((!myStack.isEmpty()) && isOpposite(myStack.peekLast(), line.charAt(i)+"")){
               myStack.removeLast();
            } else {
               myStack.add(line.charAt(i)+"");
            }
         }
      }
      return ""+myStack.size();
   }

   @Override
   public String processPart2() {
      int shortedItem = Integer.MAX_VALUE;
      for (int i = 65; i <= 90; i++){
         fillDataStruct();
         toRemove = ""+((char)i);
         int currentResult = Integer.valueOf(processPart1());
         if (shortedItem > currentResult){
            shortedItem = currentResult;
         }
      }
      return ""+shortedItem;
   }
}