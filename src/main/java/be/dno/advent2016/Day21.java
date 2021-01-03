package be.dno.advent2016;

import java.util.Arrays;
import java.util.Collections;

import be.dno.Day;
import be.dno.Utils;

public class Day21 extends Day{

   
   public Day21(){
      fileName = "2016/day21.txt";
   }

  
   public void fillDataStruct(){

   }

   @Override
   public String processPart1() {

      char[] initPasswd = "abcdefgh".toCharArray();
      //char[] initPasswd = "abcde".toCharArray();
      for (String line : lines){
         //System.out.println(line);
         if (line.startsWith("swap position")){
            Integer[] data = Utils.extractNumbers(line);
            initPasswd = swapPositions(initPasswd, data);
         } else if (line.startsWith("swap letter")){ //swap letter g with letter b
            String[] letters = line.replace("swap letter ", "").split(" with letter ");
            char a = letters[0].charAt(0);
            char b = letters[1].charAt(0);
            initPasswd = swapLetters(initPasswd, a, b);
         } else if (line.startsWith("rotate left")){
            int offset = Utils.extractNumbers(line)[0];
            initPasswd = rotateLeft(initPasswd, offset);
         } else if (line.startsWith("rotate right")){
            int offset = Utils.extractNumbers(line)[0];
            initPasswd = rotateRight(initPasswd, offset);
         } else if (line.startsWith("rotate based on position of letter")){
            char letter = line.charAt(line.length()-1);
            initPasswd = rotateBasedLetter(initPasswd, letter);
         } else if (line.startsWith("reverse positions")){
            Integer[] offsets = Utils.extractNumbers(line);
            initPasswd = reversePos(initPasswd, offsets);
         } else if (line.startsWith("move position")){
            Integer[] offsets = Utils.extractNumbers(line);
            initPasswd = movePosition(initPasswd, offsets);
         }
         //System.out.println("\t"+new String(initPasswd));
      }
      return new String(initPasswd);
   }

   private char[] movePosition(char[] initPasswd, Integer[] offsets){
      char[] work = new char[initPasswd.length];
      Arrays.fill(work, ' ');
      work[offsets[1]] = initPasswd[offsets[0]];
      initPasswd[offsets[0]] = ' ';
      int idx_init = 0;
      int idx_work = 0;
      while(idx_work < initPasswd.length){
         if (work[idx_work] != ' '){
            idx_work++;
            continue;
         }
         if (initPasswd[idx_init] != ' '){
            work[idx_work] = initPasswd[idx_init];
            idx_work++;
         }
         idx_init++;
      }
      return work;
   }

   private char[] reversePos(char[] initPasswd, Integer[] offsets){
      int idx = 0;
      char[] work = Utils.copy(initPasswd);
      
      char[] to_reverse = Arrays.copyOfRange(work, offsets[0],  offsets[1]+1);

      char[] dest_array = new char[to_reverse.length]; 
      int j = to_reverse.length; 
      for (int i = 0; i < to_reverse.length; i++) { 
         dest_array[j - 1] = to_reverse[i]; 
         j = j - 1; 
      } 
      for (int i = 0; i < work.length; i++){
         if (i >= offsets[0] && i <= offsets[1]){
            work[i] = dest_array[idx];
            idx++;
         }
      }
      
      return work;
   }

   private char[] rotateLeft(char[] initPasswd, int rotations){
      if (rotations == 0) return initPasswd;
      char[] work = Utils.copy(initPasswd);
      for (int rotation = 0; rotation < rotations; rotation++){
         char[] result = Utils.copy(work);
         for (int i = 0; i < work.length; i++){
            int iOff = i+1;
            if (iOff == initPasswd.length) iOff = 0;
            work[i] = result[iOff];
         }
      }
      return work;
   }

   private char[] rotateRight(char[] initPasswd, int rotations){
      if (rotations == 0) return initPasswd;
      if (rotations == initPasswd.length) return initPasswd;
      char[] work = Utils.copy(initPasswd);
      
      for (int rotation = 0; rotation < rotations; rotation++){
         char[] result = Utils.copy(work);
         for (int i = 0; i < work.length; i++){
            int iOff = i-1;
            if (iOff == -1) {
               iOff = work.length -1;
            }
            work[i] = result[iOff];
         }
      }
      return work;
   }

   private char[] swapPositions(char[] initPasswd, Integer[] data){
      char[] work = Utils.copy(initPasswd);
      char c = work[data[0]];
      work[data[0]] = work[data[1]];
      work[data[1]] = c;
      return work;
   }

   private char[] swapLetters(char[] initPasswd, char a, char b){
      char[] work = Utils.copy(initPasswd);
      for (int i = 0; i < work.length; i++){
         if (work[i] == a){
            work[i] = b;
         } else if (work[i] == b){
            work[i] = a;
         }
      }
      return work;
   }

   private char[] rotateBasedLetter(char[] initPasswd,char letter){
      int idx = -1;
      for (int i = 0; i < initPasswd.length; i++){
         if (letter == initPasswd[i]){
            idx = i;
            break;
         }
      }
      if (idx >= 4) idx++;
      idx++;
      return rotateRight(initPasswd, idx);
   }

   @Override
   public String processPart2() {
      char[] initPasswd = "fbgdceah".toCharArray();
      Collections.reverse(lines);
      for (String line : lines){
         //System.out.println(line);
         if (line.startsWith("swap position")){
            Integer[] data = Utils.extractNumbers(line);
            initPasswd = swapPositions(initPasswd, data);
         } else if (line.startsWith("swap letter")){ //swap letter g with letter b
            String[] letters = line.replace("swap letter ", "").split(" with letter ");
            char a = letters[0].charAt(0);
            char b = letters[1].charAt(0);
            initPasswd = swapLetters(initPasswd, a, b);
         } else if (line.startsWith("rotate left")){
            int offset = Utils.extractNumbers(line)[0];
            initPasswd = rotateRight(initPasswd, offset);
         } else if (line.startsWith("rotate right")){
            int offset = Utils.extractNumbers(line)[0];
            initPasswd = rotateLeft(initPasswd, offset);
         } else if (line.startsWith("rotate based on position of letter")){
            //do brute force here...
            char letter = line.charAt(line.length()-1);
            char[] generated = initPasswd;
            while(true){
               char[] replacedLetter = rotateBasedLetter(generated, letter);
               if (equals(initPasswd, replacedLetter)){
                  initPasswd = Utils.copy(generated);
                  break;
               }
               generated = rotateRight(generated, 1);
            }
         } else if (line.startsWith("reverse positions")){
            Integer[] offsets = Utils.extractNumbers(line);
            initPasswd = reversePos(initPasswd, offsets);
         } else if (line.startsWith("move position")){
            Integer[] offsets = Utils.extractNumbers(line);
            Integer d = offsets[0];
            offsets[0] = offsets[1];
            offsets[1] = d;
            initPasswd = movePosition(initPasswd, offsets);
         }
         //System.out.println("\t"+new String(initPasswd));
      }
      return new String(initPasswd);
   }

   private boolean equals(char[] target, char[] generated) {
      return Arrays.equals(target, generated);
   }

}
