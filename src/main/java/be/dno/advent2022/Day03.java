package be.dno.advent2022;

import java.util.HashSet;
import java.util.Set;

import be.dno.Day;

public class Day03 extends Day {

   public Day03(){
      fileName = "2022/day03.txt";
   }

   private void addCommon(Set<Character> set, String str1, String str2){
      for (int i = 0; i < str1.length(); i++){
         if (str2.indexOf(str1.charAt(i)) > -1){
            set.add(Character.valueOf(str1.charAt(i)));
         }
      }
   }

   private int getPriority(Character c){
      int ret = 0;
      int asciiVal = (int)c.charValue();
      if (asciiVal >= 97 && asciiVal <= 122) ret = asciiVal - (97-1);
      if (asciiVal >= 65 && asciiVal <= 90)  ret = asciiVal - (65-27);
      return ret;
   }

   @Override
   public String processPart1() {
      int totalPriorities = 0;
      for (String line : lines){
         Set<Character> commonSet = new HashSet<>();
         String comp1 = line.substring(0, line.length()/2);
         String comp2 = line.substring(line.length()/2);
         addCommon(commonSet, comp1, comp2);
         for (Character commonChar : commonSet.toArray(new Character[0])){
            totalPriorities += getPriority(commonChar);
         }
         
      }
      return String.valueOf(totalPriorities);
   }

   @Override
   public String processPart2() {
      int totalPriorities = 0;
      int elvesCount = 0;
      Set<Character> commonSet = new HashSet<>();
      for (String line : lines){
         elvesCount++;
         
         if (elvesCount == 1){
            //all all char as common
            for (Character c : line.toCharArray()){
               commonSet.add(c);
            }
         } else {
            //reduce commonSet if not found
            Set<Character> toRemove = new HashSet<>();
            for (Character c : commonSet.toArray(new Character[0])){
               if (line.indexOf(c.charValue()) == -1){
                  toRemove.add(c);
               }
            }
            commonSet.removeAll(toRemove);
         }
         if (elvesCount == 3){
            for (Character commonChar : commonSet.toArray(new Character[0])){
               totalPriorities += getPriority(commonChar);
            }
            elvesCount = 0;
            commonSet = new HashSet<>();
         }

      }
      
      return String.valueOf(totalPriorities);
   }
}

