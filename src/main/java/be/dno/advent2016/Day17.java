package be.dno.advent2016;

import org.apache.commons.codec.digest.DigestUtils;

import be.dno.Day;

public class Day17 extends Day{
   

   public Day17(){
      fileName = null;
   }

   public void fillDataStruct(){
      
   }

   private String getHash(String in){
      return DigestUtils.md5Hex(in).toLowerCase();
   }


   @Override
   public String processPart1() {
      String step1 = getHash("hijkl").substring(0, 4);
      return step1;
   }

   
   @Override
   public String processPart2() {
      return "";
   }

}
