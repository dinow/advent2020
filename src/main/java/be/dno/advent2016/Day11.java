package be.dno.advent2016;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import be.dno.Day;

public class Day11 extends Day{

   private final Map<String, Set<String>> floors = new HashMap<>();
   private int elevatorFloor = 1;
   public Day11(){
      fileName = "2016/day11.txt";
   }

   @Override
   public void readLines() throws IOException {
   }

   @Override
   public void fillDataStruct(){
      floors.put("1", new HashSet<String>());
      floors.put("2", new HashSet<String>());
      floors.put("3", new HashSet<String>());
      floors.put("4", new HashSet<String>());

      floors.get("1").add("HM");
      floors.get("1").add("LM");
      floors.get("2").add("HG");
      floors.get("3").add("LG");
   }

   @Override
   public String processPart1() {
      while(part1ongoing()){
         //check items on current floor
         Set<String> items = floors.get(elevatorFloor+"");
         Set<String> itemsNext = floors.get((elevatorFloor+1)+"");

         //compute all possible moves for these items
         Set<String> setTest = new HashSet<>(itemsNext);

         Set<Set<String>> nextStates = new HashSet<>();
         setTest = new HashSet<>(itemsNext);
         while(!items.isEmpty()){
            String itemToAdd = items.iterator().next();
            setTest.add(itemToAdd);
            items.remove(itemToAdd);
            if (isSafe(setTest)){
               Set<String> ns = new HashSet<>();
               ns.addAll(setTest);
               nextStates.add(ns);
            }
         }
      }
      return "";
   }

   private boolean isSafe(Set<String> set){
      //no xG without xM and another zM because zM would fry
      Set<String> cleanedSet = removeCouples(set);
      return cleanedSet.isEmpty();
   }

   private Set<String> removeCouples(Set<String> set) {
      Set<String> cleaned = new HashSet<>(set);
      for (String s : set){
         String opposite;
         if (s.endsWith("M")) opposite = s.replace("M", "G");
         opposite = s.replace("G", "M");
         if (set.contains(opposite)){
            cleaned.remove(s);
            cleaned.remove(opposite);
         }
      }
      for (String s : set){
         if (s.endsWith("M")) cleaned.remove(s);
      }

      return cleaned;
   }

   @Override
   public String processPart2() {
      return "";
   }

   private boolean part1ongoing(){
      return (!floors.get("1").isEmpty()) || (!floors.get("2").isEmpty()) || (!floors.get("3").isEmpty());
   }
}
