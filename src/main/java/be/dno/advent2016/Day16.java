package be.dno.advent2016;

import be.dno.Day;

public class Day16 extends Day{


   public Day16(){
      fileName = null;
   }

   public void fillDataStruct(){
      
   }

   private String generateLongString(String a, int desiredLength){
      String result = a;
      while (result.length() < desiredLength){
         result = _genStr(result);
      }
      return result.substring(0, desiredLength);
   }

   private String _genStr(String _a){
      StringBuilder a = new StringBuilder(_a);
      StringBuilder b = new StringBuilder(_a).reverse();
      return a.append("0").append(b.toString().replaceAll("0", "t").replaceAll("1", "0").replaceAll("t","1")).toString();
   }

   private String generateOddCS(String input){
      StringBuilder sb = new StringBuilder();
      String str = input;
      while (sb.length() == 0 || sb.length() % 2 == 0){
         sb.setLength(0);
         char[] in = str.toCharArray();
         for (int i = 0; i < in.length; i+=2){
            char a = in[i];
            char b = in[i+1];
            if (a == b) sb.append("1"); else sb.append("0");
         }
         str = sb.toString();
      }
      return str;
   }

   @Override
   public String processPart1() {
      return generateOddCS(generateLongString("11110010111001001", 272));
   }

   
   @Override
   public String processPart2() {
      return generateOddCS(generateLongString("11110010111001001", 35651584));
   }

}
