package be.dno.advent2022;

import java.util.ArrayDeque;
import java.util.Iterator;

import be.dno.Day;
import be.dno.Utils;

public class Day05 extends Day {
   ArrayDeque<String>[] crates;
   public Day05(){
      fileName = "2022/day05.txt";
   }

   @Override
   public void fillDataStruct() {
      String prevLine = null;
      int totalCrates = -1;
      for (String line : lines){
         if (prevLine != null && line.length() ==0 ){
            totalCrates = prevLine.split(" ").length;
            break;
         }
         prevLine = line.replace("   ", " ").trim();
      }
      crates = new ArrayDeque[totalCrates];
      for (int i = 0; i < totalCrates; i++){
         crates[i] = new ArrayDeque<String>();
      }
      for (String line : lines){
         if (!line.contains("[")){
            break;
         }
         int cratePos = 1;
         for (int i = 0; i < totalCrates; i++){
            cratePos = (i*4)+1;
            if (line.length() > cratePos){
               String crate = String.valueOf(line.charAt(cratePos)).trim();
               if (crate.length() != 0){
                  crates[i].add(String.valueOf(line.charAt(cratePos)));
               }
            }
         }
      }
      for (int i = 0; i < crates.length; i++){
         crates[i] = reverse(crates[i]);
      }
   }

   private ArrayDeque<String> reverse(ArrayDeque<String> arrayDeque) {
      ArrayDeque<String> reversed = new ArrayDeque<>();
      Iterator<String> it = arrayDeque.descendingIterator();
      while (it.hasNext()){
         reversed.add(it.next());
      }
      return reversed;
   }

   @Override
   public String processPart1() {
      for (String line : lines){
         if (!line.startsWith("move")){
            continue;
         }
         Integer[] numbers = Utils.extractNumbers(line);
         //move 1 from 2 to 1
         for (int i = 0; i < numbers[0]; i++){
            String item = crates[numbers[1]-1].removeLast();
            crates[numbers[2]-1].add(item);
         }
      }
      StringBuilder result = new StringBuilder();
      for (int i = 0; i < crates.length; i++){
         result.append(crates[i].removeLast());
      }
      return result.toString();
   }

   @Override
   public String processPart2() {
      for (String line : lines){
         if (!line.startsWith("move")){
            continue;
         }
         Integer[] numbers = Utils.extractNumbers(line);
         //move 1 from 2 to 1
         ArrayDeque<String> tempDeque = new ArrayDeque<>();
         for (int i = 0; i < numbers[0]; i++){
            String item = crates[numbers[1]-1].removeLast();
            tempDeque.add(item);
         }
         Iterator<String> it = tempDeque.descendingIterator();
         while (it.hasNext()){
            crates[numbers[2]-1].add(it.next());
         }
      }
      StringBuilder result = new StringBuilder();
      for (int i = 0; i < crates.length; i++){
         result.append(crates[i].removeLast());
      }
      return result.toString();
   }
}

