package be.dno.advent2020;

import java.util.HashMap;
import java.util.Map;

import be.dno.Day;

public class Day23 extends Day{
   private Day23Cup firstCup = null;
   private String input;
   private Map<Integer, Day23Cup> mapOfCups = new HashMap<>();

   @Override
   public void fillDataStruct() {
      input = lines.get(0);
      firstCup = null;
      Day23Cup currentCup = null;
      for(char cupNumber : input.toCharArray()){
         Day23Cup curCup = new Day23Cup(String.valueOf(cupNumber));
         mapOfCups.put(curCup.toInt(), curCup);
         if (firstCup == null){
            firstCup = curCup;
            currentCup = firstCup;
         } else {
            currentCup.next = curCup;
            currentCup = curCup;
         }
      }
      currentCup.next = firstCup;
   }

   public void fillDataStruct2() {
      firstCup = null;
      mapOfCups.clear();
      Day23Cup currentCup = null;
      int highest = 0;
      for(char cupNumber : input.toCharArray()){
         Day23Cup curCup = new Day23Cup(String.valueOf(cupNumber));
         mapOfCups.put(curCup.toInt(), curCup);
         if (curCup.toInt() > highest) highest = curCup.toInt();
         if (firstCup == null){
            firstCup = curCup;
            currentCup = firstCup;
         } else {
            currentCup.next = curCup;
            currentCup = curCup;
         }
      }
      int curNumber = highest+1;
      while (curNumber <= 1_000_000){
         Day23Cup curCup = new Day23Cup(curNumber);
         mapOfCups.put(curCup.toInt(), curCup);
         currentCup.next = curCup;
         currentCup = curCup;
         curNumber++;
      }
      currentCup.next = firstCup;
   }

   

   @Override
   public String processPart1() {
      processCrabGame(100);
      return getCups(getCupFromNumber(1).next);
   }

   private boolean isInPickUp(int currentCupValue, Day23Cup startCut) {
      Day23Cup nextCup = startCut;
      while(nextCup != null){
         if (currentCupValue == nextCup.number) return true;
         nextCup = nextCup.next;
      }
      return false;
   }

   private String getCups(Day23Cup cup) {
      StringBuilder cups = new StringBuilder();
      cups.append(cup);
      Day23Cup nextCup = cup.next;
      while(nextCup != null && nextCup.number != cup.number){
         cups.append(nextCup);
         nextCup = nextCup.next;
      }
      return cups.toString();
   }
   private Day23Cup getCupFromNumber(int number) {
      return mapOfCups.get(number);
   }

   private void processCrabGame(int max){
      Day23Cup currentCup = firstCup;
      for (int i = 0; i < max; i++){
         /*
            The crab picks up the three cups that are immediately clockwise of the current cup. 
            They are removed from the circle; cup spacing is adjusted as necessary to maintain the circle.
         */
         Day23Cup startCut = currentCup.next;
         Day23Cup newTail = startCut.next.next.next;
         currentCup.next = newTail;
         startCut.next.next.next = null;
         /*
            The crab selects a destination cup: the cup with a label equal to the current cup's label minus one. 
            If this would select one of the cups that was just picked up, the crab will keep subtracting one until it finds a cup that wasn't just picked up. 
            If at any point in this process the value goes below the lowest value on any cup's label, it wraps around to the highest value on any cup's label instead.
         */
         Day23Cup destinationCup = currentCup.next;
         int currentCupValue = currentCup.number;
         if (currentCupValue == 1){
            currentCupValue = mapOfCups.size()+1;
         } 
         currentCupValue--;

         boolean inPickUp = isInPickUp(currentCupValue, startCut);
         while(inPickUp){
            if (currentCupValue == 1){
               currentCupValue = mapOfCups.size()+1;
            } 
            currentCupValue--;
            inPickUp = isInPickUp(currentCupValue, startCut);
         }
         destinationCup = mapOfCups.get(currentCupValue);
         
         /*
            The crab places the cups it just picked up so that they are immediately clockwise of the destination cup. 
            They keep the same order as when they were picked up.
         */
         startCut.next.next.next = destinationCup.next;
         destinationCup.next = startCut;

         /*
            The crab selects a new current cup: the cup which is immediately clockwise of the current cup.
         */
         currentCup = currentCup.next;
      }
   }

   @Override
   public String processPart2() {
      fillDataStruct2();
      processCrabGame(10_000_000);
      Day23Cup cupOne = getCupFromNumber(1);
      System.out.println(cupOne.next);
      System.out.println(cupOne.next.next);
      return ""+(Long.valueOf(cupOne.next.toInt()) * Long.valueOf(cupOne.next.next.toInt()));
   }

}
