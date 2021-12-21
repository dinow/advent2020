package be.dno.advent2021;

import be.dno.Day;

public class Day21 extends Day {

   private int dice;
   private long rollDiceCount;
   private long[] playerScores   = new long[2];
   private int[] playerPositions = new int[2];
   private long[] playerWins     = new long[2];

   @Override
   public void fillDataStruct(){
      dice = 0;
      rollDiceCount = 0l;
      playerScores[0] = 0l;
      playerScores[1] = 0l;
      playerWins[0] = 0l;
      playerWins[1] = 0l;
      playerPositions[0] = 4;
      playerPositions[1] = 8;
   }

   @Override
   public String processPart1(){ 
      long step = 0;
      int playerToPlay = 0;
      while(playerScores[0] < 1000 && playerScores[1] < 1000){
         playerToPlay =  (step % 2 == 0) ? 0 : 1;
         for (int i = 0; i < 3; i++){
            rollDice();
            for (int j = 0; j < dice; j++){
               playerPositions[playerToPlay]++;
               if (playerPositions[playerToPlay] > 10) playerPositions[playerToPlay] = 1; 
            }
         }
         playerScores[playerToPlay] += playerPositions[playerToPlay];
         step++;
      }
      return (Math.min(playerScores[0],playerScores[1])*rollDiceCount)+"";
   }

   @Override
   public String processPart2(){ 
      
      return Math.max(playerWins[0],playerWins[1])+"";
   }


   public void rollDice(){
      rollDiceCount++;
      dice++;
      if (dice > 100) dice = 1;
   }
   
  
   
  
}
