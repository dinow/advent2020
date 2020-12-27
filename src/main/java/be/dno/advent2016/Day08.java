package be.dno.advent2016;

import be.dno.Day;
import be.dno.Utils;

public class Day08 extends Day{
   private final int WIDTH = 50;
   private final int HEIGHT = 6;
   private final char[][] screen = new char[HEIGHT][WIDTH];

   public Day08(){
      fileName = "2016/day08.txt";
   }

   @Override
   public void fillDataStruct() {
      Utils.initMatrix(screen, ' ');
   }

   @Override
   public String processPart1() {
      for (String line : lines){
         processLine(line);
      }
      
      return ""+Utils.countCharInArray(screen, '#');
   }

   @Override
   public String processPart2() {
      Utils.printMatrix(screen);
      return "";
   }

   private void processLine(String line){
      //System.out.println(line);
      String workingLine = line;
      if (line.startsWith("rect")){
         int[] data = Utils.splitCouple(workingLine.replace("rect ", ""), "x");
         for (int i = 0; i < data[1]; i++){
            for (int j = 0; j < data[0]; j++){
               screen[i][j] = '#';
            }
         }
      } else if (line.startsWith("rotate row")){
         int[] data = Utils.splitCouple(workingLine.replace("rotate row y=", ""), " by ");
         char [] tempLine = new char[WIDTH];
         for (int i = 0; i < tempLine.length; i++){
            int newIdx = i+data[1];
            if (newIdx > tempLine.length-1){
               newIdx %= tempLine.length;
            }
            tempLine[newIdx] = screen[data[0]][i];
         }
         for (int i = 0; i < tempLine.length; i++){
            screen[data[0]][i] = tempLine[i];
         }
      } else if (line.startsWith("rotate column")){
         int[] data = Utils.splitCouple(workingLine.replace("rotate column x=", ""), " by ");
         char [] tempLine = new char[HEIGHT];
         for (int i = 0; i < tempLine.length; i++){
            int newIdx = i+data[1];
            if (newIdx > tempLine.length-1){
               newIdx %= tempLine.length;
            }
            tempLine[newIdx] = screen[i][data[0]];
         }
         for (int i = 0; i < tempLine.length; i++){
            screen[i][data[0]] = tempLine[i];
         }
      } else {
         System.out.println("Unknow command ["+line+"]");
      }
      //Utils.printMatrix(screen);
   }

   
}
