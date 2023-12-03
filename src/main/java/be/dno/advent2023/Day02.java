package be.dno.advent2023;


import java.util.ArrayList;
import java.util.List;

import be.dno.Day;
import be.dno.Utils;

public class Day02 extends Day {


   public Day02(){
      fileName = "2023/day02.txt";
   }

   @Override
   public void fillDataStruct() {
      
   }


   @Override
   public String processPart1() {
      long totalScore = 0l;
      linesLoop:for (String line : lines){
         boolean linePossible = true;
         String[] idAndCubes = line.split(":");
         int gameId = Utils.extractNumbers(idAndCubes[0])[0].intValue();
         String[] games = idAndCubes[1].split(";");
         gamesLoop:for (String game : games){
            String[] cubes = game.split(",");
            cubesLoop: for (String cube : cubes){
               int numberOfCubes = Utils.extractNumbers(cube)[0].intValue();
               if (cube.contains("red") && numberOfCubes > 12){
                  linePossible = false;
                  break gamesLoop;
               }
               if (cube.contains("green") && numberOfCubes > 13){
                  linePossible = false;
                  break gamesLoop;
               }
               if (cube.contains("blue") && numberOfCubes > 14){
                  linePossible = false;
                  break gamesLoop;
               }
            }
            
         }
         if (linePossible){
            totalScore += gameId;
         }
      }
      return totalScore+"";
   }

   @Override
   public String processPart2() {
      long totalScore = 0l;
      for (String line : lines){
         String[] idAndCubes = line.split(":");
         int gameId = Utils.extractNumbers(idAndCubes[0])[0].intValue();
         String[] games = idAndCubes[1].split(";");
         int minRed = Integer.MIN_VALUE;
         int minGreen = Integer.MIN_VALUE;
         int minBlue = Integer.MIN_VALUE;
         for (String game : games){
            String[] cubes = game.split(",");
            for (String cube : cubes){
               int numberOfCubes = Utils.extractNumbers(cube)[0].intValue();
               if (cube.contains("red") && numberOfCubes > minRed){
                  minRed = numberOfCubes;
               }
               if (cube.contains("green") && numberOfCubes > minGreen){
                  minGreen = numberOfCubes;
               }
               if (cube.contains("blue") && numberOfCubes > minBlue){
                  minBlue = numberOfCubes;
               }
            }
         }

         totalScore += minRed * minBlue * minGreen;
      }
      return totalScore+"";
   }
}
