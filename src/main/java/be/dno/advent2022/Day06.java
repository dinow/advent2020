package be.dno.advent2022;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import be.dno.Day;

public class Day06 extends Day {
   ArrayDeque<String>[] crates;
   public Day06(){
      fileName = "2022/day06.txt";
   }

   private int findData(String line, int number){
      ArrayDeque<Character> currentBuffer = new ArrayDeque<>();
      int cpt = 0;
      for (char c : line.toCharArray()){
         cpt++;
         currentBuffer.add(c);
         if (currentBuffer.size() < number){
            continue;
         }
         if (allDifferent(currentBuffer)){
            return cpt;
         }
         currentBuffer.removeFirst();
      }
      return -1;
   }

   @Override
   public void fillDataStruct() {

   }


   @Override
   public String processPart1() {
      for (String line : lines){
         System.out.println(findData(line, 4));
      }
      return "";
   }

   private boolean allDifferent(ArrayDeque<Character> currentBuffer) {
      Set<Character> unique = new HashSet<>();
      Iterator<Character> it = currentBuffer.iterator();
      while(it.hasNext()){
         unique.add(it.next());
      }
      return unique.size() == currentBuffer.size();
   }

   @Override
   public String processPart2() {
      for (String line : lines){
         System.out.println(findData(line, 14));
      }
      return "";
   }
}

