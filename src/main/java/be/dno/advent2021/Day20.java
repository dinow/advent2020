package be.dno.advent2021;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import be.dno.Day;

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
      return goForIt(2)+"";
   }
   
   @Override
   public String processPart2(){ 
      return goForIt(50)+"";
   }
   
   private long goForIt(int steps) {
	   for (int i = 0; i < steps; i++){
	         Map<Point, Character> adddedPoints = new HashMap<>();
	         int minY = getMinY();
	         int maxY = getMaxY();
	         int minX = getMinX();
	         int maxX = getMaxX();
	         for (int y = minY - 2; y < maxY + 2; y++){
	            for (int x = minX - 2; x < maxX + 2; x++){
	               Point p = new Point(x, y);
	               String bitMask   = getBitMask(i, x, y);
	               int indxValue    = Integer.valueOf(bitMask, 2);
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
	      return nbChars;
   }
   
   private String getBitMask(int step, int x, int y) {
	   return getBit(step, new Point(x-1, y-1))
               + getBit(step, new Point(x  , y-1))
               + getBit(step, new Point(x+1, y-1))
               + getBit(step, new Point(x-1, y))
               + getBit(step, new Point(x  , y))
               + getBit(step, new Point(x+1, y))
               + getBit(step, new Point(x-1, y+1))
               + getBit(step, new Point(x  , y+1))
               + getBit(step, new Point(x+1, y+1));
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
