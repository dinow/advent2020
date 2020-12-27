package be.dno.advent2016;


import be.dno.Day;
import be.dno.Utils;

public class Day09 extends Day{

   @Override
   public String processPart1() {
      long size = 0l;
      for (String line : lines){
         size += computeSize(line);
      }
      return ""+size;
   }

   @Override
   public String processPart2() {
      long size = 0l;
      for (String line : lines){
         size += computeSizeRec(line);
      }
      return ""+size;
   }

   private long computeSizeRec(String line){
      int indexOfOpen = line.indexOf("(");
      if (indexOfOpen == -1){
         return line.length();
      }
      long size = 0;
      while(indexOfOpen != -1){
         String repeating = line.substring(indexOfOpen, line.indexOf(")", indexOfOpen)+1);
         int[] data = Utils.splitCouple(repeating.replaceAll("\\(", "").replaceAll("\\)",""), "x");
         int startGet = indexOfOpen+repeating.length();
         int endGet = startGet+data[0];
         String toRepeat = line.substring(startGet, endGet);
         indexOfOpen = endGet;

         
         if (toRepeat.indexOf("(") > -1){
            size += data[1] * computeSizeRec(toRepeat);
         } else {
            size += data[0] * data[1];
         }

         line = line.substring(endGet);
         indexOfOpen = line.indexOf("(");
         if (indexOfOpen > 0){
            size += computeSizeRec(line.substring(0, indexOfOpen));
            line = line.substring(indexOfOpen);
            indexOfOpen = line.indexOf("(");
         }
      }
      return size;
   }

   private long computeSize(String line){
      int indexOfOpen = line.indexOf("(");
      if (indexOfOpen == -1){
         return line.length();
      }
      long size = 0;
      while(indexOfOpen != -1){
         String repeating = line.substring(indexOfOpen, line.indexOf(")", indexOfOpen)+1);
         int[] data = Utils.splitCouple(repeating.replaceAll("\\(", "").replaceAll("\\)",""), "x");
         int startGet = indexOfOpen+repeating.length();
         int endGet = startGet+data[0];
         String toRepeat = line.substring(startGet, endGet);
         indexOfOpen = endGet;
         size += toRepeat.length() * data[1];
         line = line.substring(0, indexOfOpen) + line.substring(endGet);
         indexOfOpen = line.indexOf("(", indexOfOpen);
      }
      return size;
   }
}
