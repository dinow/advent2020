package be.dno.advent2022;


import java.util.ArrayList;
import java.util.List;

import be.dno.Day;

public class Day02 extends Day {
   private List<String[]> games;

   private final static int ROCK = 1;
   private final static int PAPER = 2;
   private final static int SCISSOR = 3;

   public Day02(){
      fileName = "2022/day02.txt";
    }

   @Override
   public void fillDataStruct() {
      games = new ArrayList<>();
      for(String line : lines){
         games.add(line.split(" "));
      }
   }


   @Override
   public String processPart1() {
      long totalScore = 0l;
      for (String[] game : games){
         int scoreShape = ((int)game[1].charAt(0))-87; //1, 2, 3
         int scoreOpponent = ((int)game[0].charAt(0))-64;
         int score = scoreShape;
         if (scoreShape == scoreOpponent){
            score += 3;
         }
         if (scoreShape == ROCK && scoreOpponent == SCISSOR){
            score += 6;
         }
         if (scoreShape == PAPER && scoreOpponent == ROCK){
            score += 6;
         }
         if (scoreShape == SCISSOR && scoreOpponent == PAPER){
            score += 6;
         }
         if (scoreShape == ROCK && scoreOpponent == PAPER){
            score += 0;
         }
         if (scoreShape == PAPER && scoreOpponent == 3){
            score += 0;
         }
         if (scoreShape == SCISSOR && scoreOpponent == ROCK){
            score += 0;
         }
         totalScore += (long)score;
      }
      return totalScore+"";
   }

   @Override
   public String processPart2() {
      long totalScore = 0l;
      for (String[] game : games){
         int scoreShape = ((int)game[1].charAt(0))-87; //1, 2, 3
         int scoreOpponent = ((int)game[0].charAt(0))-64;
         switch (scoreShape){
            case 1:
            //need to lose
            if (scoreOpponent == ROCK){
               scoreShape = SCISSOR;
            }
            if (scoreOpponent == PAPER){
               scoreShape = ROCK;
            }
            if (scoreOpponent == SCISSOR){
               scoreShape = PAPER;
            }
            break;
            case 2:
            //need to draw
            scoreShape = scoreOpponent;
            break;
            case 3:
            //need to win
            if (scoreOpponent == ROCK){
               scoreShape = PAPER;
            }
            if (scoreOpponent == PAPER){
               scoreShape = SCISSOR;
            }
            if (scoreOpponent == SCISSOR){
               scoreShape = ROCK;
            }
            break;
         }
         int score = scoreShape;
         if (scoreShape == scoreOpponent){
            score += 3;
         }
         if (scoreShape == ROCK && scoreOpponent == SCISSOR){
            score += 6;
         }
         if (scoreShape == PAPER && scoreOpponent == ROCK){
            score += 6;
         }
         if (scoreShape == SCISSOR && scoreOpponent == PAPER){
            score += 6;
         }
         if (scoreShape == ROCK && scoreOpponent == PAPER){
            score += 0;
         }
         if (scoreShape == PAPER && scoreOpponent == SCISSOR){
            score += 0;
         }
         if (scoreShape == SCISSOR && scoreOpponent == ROCK){
            score += 0;
         }
         totalScore += (long)score;
      }
      return totalScore+"";
   }
}
