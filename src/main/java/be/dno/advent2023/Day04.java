package be.dno.advent2023;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import be.dno.Day;
import be.dno.MatrixElementChar;
import be.dno.Utils;

public class Day04 extends Day {

   int[] cards;

   public Day04(){
      fileName = "2023/day04.txt";
   }

   @Override
   public void fillDataStruct() {
   }


   @Override
   public String processPart1() {
      long totalScore = 0l;
      for (String line : lines){
         String[] firstSplit = line.split(":");
         String[] secondSplit = firstSplit[1].split("\\|");
         String[] winnings = secondSplit[0].split(" ");
         String[] myNumbers = secondSplit[1].split(" ");
         Set<String> winSet = new HashSet<String>(Arrays.asList(winnings));
         int points = 0;
         for (String my : myNumbers){
            if (!my.trim().isEmpty() && winSet.contains(my.trim())){
               if (points == 0){
                  points = 1;
               } else {
                  points *= 2;
               }

            }
         }
         totalScore += points;


      }
      return totalScore+"";
   }

  
   @Override
   public String processPart2() {
      cards = new int[lines.size()+1];
      Utils.initArray(cards, 1);
      cards[0] = 0;
      long totalScore = 0l;
      for (String line : lines){
         String[] firstSplit = line.split(":");
         int gameNum = Integer.valueOf(firstSplit[0].replaceAll("Card ", "").trim()).intValue();
         String[] secondSplit = firstSplit[1].split("\\|");
         String[] winnings = secondSplit[0].split(" ");
         String[] myNumbers = secondSplit[1].split(" ");
         Set<String> winSet = new HashSet<String>(Arrays.asList(winnings));
         int points = 0;
         for (String my : myNumbers){
            if (!my.trim().isEmpty() && winSet.contains(my.trim())){
               points++;
            }
         }
         for (int i = gameNum+1; i < gameNum + points + 1; i++){
            cards[i] = cards[i] + 1*cards[gameNum];
         }
      }
      for (int i = 0; i < cards.length; i++){
         totalScore += cards[i];
      }
      return totalScore+"";
   }
}
