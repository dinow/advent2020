package be.dno.advent2021;

import java.util.HashMap;
import java.util.Map;
import java.awt.Point;

import be.dno.Day;
public class Day25 extends Day {

   private Map<Point, Character> seacucumbers;
   private int boundarieY, boundarieX;
   public Day25(){
      fileName = "2021/day25.txt";
   }

   @Override
   public void fillDataStruct(){
      seacucumbers = new HashMap<>();
      boundarieY = lines.size()-1;
      boundarieX = lines.get(0).length()-1;
      int i =  0;
      for (String line : lines){
         int j = 0;
         for (char c : line.toCharArray()){
            if (c != '.'){
               seacucumbers.put(new Point(j, i), Character.valueOf(c));
            }
            j++;
         }
         i++;
      }
   }

   @Override
   public String processPart1(){ 
      long stepCount = 0;
      boolean moved = true;
      while(moved){
         moved = false;
         Map<Point, Character> seacucumbersWork = new HashMap<>();
         for (Point p : seacucumbers.keySet()){
            Character c = seacucumbers.get(p);
            if (c.charValue() == '>'){
               //check if can move
               int nextPosX = ((int)p.getX())+1;
               if (nextPosX > boundarieX) nextPosX = 0;
               if (!seacucumbers.containsKey(new Point(nextPosX, ((int)p.getY())))){
                  seacucumbersWork.put(new Point(nextPosX, ((int)p.getY())), Character.valueOf(c));
                  moved = true;
               } else {
                  seacucumbersWork.put(new Point(((int)p.getX()), ((int)p.getY())), Character.valueOf(c)); 
               }
            }else{
               seacucumbersWork.put(new Point(((int)p.getX()), ((int)p.getY())), Character.valueOf(c)); 
            }
         }
         seacucumbers.clear();
         seacucumbers = new HashMap<>(seacucumbersWork);

         seacucumbersWork = new HashMap<>();
         for (Point p : seacucumbers.keySet()){
            Character c = seacucumbers.get(p);
           if (c.charValue() == 'v'){
               //check if can move
               int nextPosY = ((int)p.getY())+1;
               if (nextPosY > boundarieY) nextPosY = 0;
               if (!seacucumbers.containsKey(new Point( ((int)p.getX()), nextPosY))){
                  seacucumbersWork.put(new Point( ((int)p.getX()), nextPosY), Character.valueOf(c));
                  moved = true;
               }else {
                  seacucumbersWork.put(new Point(((int)p.getX()), ((int)p.getY())), Character.valueOf(c)); 
               }
            }else{
               seacucumbersWork.put(new Point(((int)p.getX()), ((int)p.getY())), Character.valueOf(c)); 
            }
         }
         seacucumbers.clear();
         seacucumbers = new HashMap<>(seacucumbersWork);
         stepCount++;
      }
      return stepCount+"";
   }
  
}