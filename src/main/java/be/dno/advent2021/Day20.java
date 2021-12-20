package be.dno.advent2021;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.awt.Point;

import be.dno.Day;
import be.dno.Utils;

public class Day20 extends Day {
   private HashMap<Point, Character> inputImage;
   private char[] inputEnhancementAlgorithm;
   
   public Day20(){
      fileName = "2021/day20.txt";
   }

   @Override
   public void fillDataStruct(){
      boolean firstDone = false;
      inputImage = new HashMap<>();
      int row = 0;
      for (String line : lines){
         if (!firstDone){
            inputEnhancementAlgorithm = line.toCharArray();
            firstDone = true;
         }else if (!line.isEmpty()){
               int col = 0;
               for (char c : line.toCharArray()){
                  inputImage.put(new Point(col, row), Character.valueOf(c));
                  col++;
               }
               row++;
         }
      }
   }

   @Override
   public String processPart1(){ 
      for (int i = 0; i < 2; i++){
         Map<Point, Character> adddedPoints = new HashMap<>();
         int minY = getMinY();
         int maxY = getMaxY();
         int minX = getMinX();
         int maxX = getMaxX();
         for (int y = minY-1; y < maxY + 1; y++){
            for (int x = minX-1; x < maxX + 1; x++){
               Point p = new Point(x, y);
               //get the 9 values surrounding
               String bitMask =    getBit(i, new Point(x-1, y-1))
                                 + getBit(i, new Point(x  , y-1))
                                 + getBit(i, new Point(x+1, y-1))
                                 + getBit(i, new Point(x-1, y))
                                 + getBit(i, new Point(x  , y))
                                 + getBit(i, new Point(x+1, y))
                                 + getBit(i, new Point(x-1, y+1))
                                 + getBit(i, new Point(x  , y+1))
                                 + getBit(i, new Point(x+1, y+1));
               int indxValue = Integer.valueOf(bitMask, 2);
               char replacement = inputEnhancementAlgorithm[indxValue];
               adddedPoints.put(p, Character.valueOf(replacement));
            }
         }
         inputImage.clear();
         inputImage.putAll(adddedPoints);
      }


      long nbChars = 0;
      for (Character c : inputImage.values()){
         if (c.charValue() == '#') nbChars++;
      }
      //not 5026, 10008, 5035, 4764
      return nbChars+"";
   }

   private int getMinY() {
      int value = Integer.MAX_VALUE;
      for (Point p : inputImage.keySet()){
         if (p.getY() < value){
            value = (int) p.getY();
         }
      }
      return value;
   }
   private int getMaxY() {
      int value = Integer.MIN_VALUE;
      for (Point p : inputImage.keySet()){
         if (p.getY() > value){
            value = (int) p.getY();
         }
      }
      return value;
   }
   private int getMinX() {
      int value = Integer.MAX_VALUE;
      for (Point p : inputImage.keySet()){
         if (p.getX() < value){
            value = (int) p.getX();
         }
      }
      return value;
   }
   private int getMaxX() {
      int value = Integer.MIN_VALUE;
      for (Point p : inputImage.keySet()){
         if (p.getX() > value){
            value = (int) p.getX();
         }
      }
      return value;
   }

   private String getBit(int step, Point p) {
      Character inputMatChar = inputImage.get(p);
      if (inputMatChar == null){
         return ""+(inputEnhancementAlgorithm[0] & step % 2);
      }
      return inputMatChar.toString().equals("#") ? "1" : "0";
   }
  
}
