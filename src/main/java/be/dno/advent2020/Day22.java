package be.dno.advent2020;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import be.dno.Day;

public class Day22 implements Day{
   private LinkedList<Integer> GLOBAL_PLAYER1;
   private LinkedList<Integer> GLOBAL_PLAYER2;


   @Override
   public void fillDataStruct(String fileName) throws IOException {
      String[] players = fileName.split(";");
      GLOBAL_PLAYER1 = buildDeck(players[0], Integer.MAX_VALUE);
      GLOBAL_PLAYER2 = buildDeck(players[1], Integer.MAX_VALUE);
   }

   public LinkedList<Integer> buildDeck(String input, int limit){
      LinkedList<Integer> player = new LinkedList<>();
      int i = 0;
      for(String card : input.split(",")){
         if (i < limit){
            player.add(Integer.parseInt(card.trim()));
            i++;
         } else {
            break;
         }
      }
      return player;
   }

   public boolean isP1Win(Integer p1card, Integer p2card){
      //game on !
      if (p1card > p2card){
         return true;
      }else{
         return false;
      }
   }
   
   private void playRound(){
      Integer p1card = GLOBAL_PLAYER1.poll();
      Integer p2card = GLOBAL_PLAYER2.poll();
      if (isP1Win(p1card, p2card)){
         GLOBAL_PLAYER1.addLast(p1card);
         GLOBAL_PLAYER1.addLast(p2card);
      }else {
         GLOBAL_PLAYER2.addLast(p2card);
         GLOBAL_PLAYER2.addLast(p1card);
      }
   }

   private int playRound1(LinkedList<Integer> player1Deck, LinkedList<Integer> player2Deck){
      Set<String> prevRounds = new HashSet<>();
      while (!(player1Deck.isEmpty() || player2Deck.isEmpty())) {
         //avoid infinite loops
         if (prevRounds.contains(player1Deck.toString()) || prevRounds.contains(player2Deck.toString()) ) {
            return 1;
         } 

         prevRounds.add(player1Deck.toString());
         prevRounds.add(player2Deck.toString());

         Integer p1card = player1Deck.poll();
         Integer p2card = player2Deck.poll();

         //check remaining stack

         int winner = 0;
         if ((player1Deck.size() >= p1card.intValue()) && (player2Deck.size() >= p2card.intValue())){
            LinkedList<Integer> player1copy = buildDeck(player1Deck.toString().replace("[", "").replace("]", "").replaceAll(" ", ""), p1card.intValue());
            LinkedList<Integer> player2copy = buildDeck(player2Deck.toString().replace("[", "").replace("]", "").replaceAll(" ", ""), p2card.intValue());
            winner = playRound1(player1copy, player2copy);
         } else {
            if (isP1Win(p1card, p2card)){
               winner = 1;
            }else {
               winner = 2;
            }
         }
         if (winner == 1){
            player1Deck.addLast(p1card);
            player1Deck.addLast(p2card);
         }else{
            player2Deck.addLast(p2card);
            player2Deck.addLast(p1card);
         }
      }
      return player1Deck.isEmpty() ? 2 : 1;
   }

   @Override
   public String processPart1() {
      while  (!(GLOBAL_PLAYER1.isEmpty() || GLOBAL_PLAYER2.isEmpty())){
         playRound();
      }
      long total = 0l;
      if (GLOBAL_PLAYER1.isEmpty()){
         total = computeDeck(GLOBAL_PLAYER2);
      } else {
         total = computeDeck(GLOBAL_PLAYER1);
      }
      return ""+total;
   }


   private long computeDeck(LinkedList<Integer> player){
      long total = 0l;
      int idx = player.size();
      Integer card = player.poll();
      total += (idx*card);
      while(card != null){
         idx--;
         card = player.poll();
         if (card != null) total += (idx*card);
      }
      return total;
   }

   @Override
   public String processPart2() {
      //The main game
      playRound1(GLOBAL_PLAYER1, GLOBAL_PLAYER2);
      long total = 0l;
      if (GLOBAL_PLAYER1.isEmpty()){
         total = computeDeck(GLOBAL_PLAYER2);
      } else {
         total = computeDeck(GLOBAL_PLAYER1);
      }
      return ""+total;
   }

}
