package be.dno.advent2022;

import java.util.ArrayList;
import java.util.List;

import be.dno.Day;

public class Day04 extends Day {
   List<int[][]> allPairs = new ArrayList<>();;

   public Day04(){
      fileName = "2022/day04.txt";
   }

   @Override
   public void fillDataStruct() {
      allPairs = new ArrayList<>(lines.size());
      for (String line : lines){
         String[] pairs = line.split(",");
         String[] pair1Split = pairs[0].split("-");
         String[] pair2Split = pairs[1].split("-");
         int[] pair1Range = new int[]{Integer.valueOf(pair1Split[0]).intValue(), Integer.valueOf(pair1Split[1]).intValue()};
         int[] pair2Range = new int[]{Integer.valueOf(pair2Split[0]).intValue(), Integer.valueOf(pair2Split[1]).intValue()};
         allPairs.add(new int[][]{pair1Range, pair2Range});
      }
   }

  
   @Override
   public String processPart1() {
      int totalOverlap = 0;
      for (int[][] pairs : allPairs){
         if (   (pairs[0][0]>=pairs[1][0] && pairs[0][1]<= pairs[1][1]) 
             || (pairs[1][0]>=pairs[0][0] && pairs[1][1]<= pairs[0][1])){
            totalOverlap++;
         }
      }
      return String.valueOf(totalOverlap);
   }

   @Override
   public String processPart2() {
      int totalOverlap = 0;
      for (int[][] pairs : allPairs){
         if (   (pairs[0][1]>=pairs[1][0] && pairs[0][0]<= pairs[1][1]) 
             || (pairs[1][1]>=pairs[0][0] && pairs[1][0]<= pairs[0][1])){
            totalOverlap++;
         }
      }
      return String.valueOf(totalOverlap);
   }
}

