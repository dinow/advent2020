package be.dno.advent2021;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.dno.Day;

public class Day18 extends Day {
  
   public Day18(){
      fileName = "2021/day18.txt";
   }

   @Override
   public void fillDataStruct(){
      
   }

   @Override
   public String processPart1(){ 
      explode("[[[[[9,8],1],2],3],4]");
      //explode("[7,[6,[5,[4,[3,2]]]]]");
      //explode("[[6,[5,[4,[3,2]]]],1]");
      //explode("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]");
      //explode("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]");
      return "";
   }
   
   public String explode(String str){
      System.out.println(str);
      StringBuilder newStr = new StringBuilder();
      //Find all couples
      char[] c_str = str.toCharArray();
      boolean hasExploded = false;
      int lValue = -1;
      int rValue = -1;
      int replacementPosition = -1;

      for (int i = 0; i < c_str.length; i++){
         if (!hasExploded && isBeginningOfCouple(c_str, i)){
            System.out.println("I found couple "+ str.substring(i, i+5));
            lValue = Integer.valueOf(String.valueOf(c_str[i+1]));
            rValue = Integer.valueOf(String.valueOf(c_str[i+3]));
            newStr.append("0");
            replacementPosition=i;
            hasExploded = true;
            i+=4;
         } else {
            newStr.append(c_str[i]);
         }
      }
      System.out.println(" -> " + newStr.toString());
      //get right digit of position
      c_str = newStr.toString().toCharArray();
      newStr.setLength(0);
      boolean hasReplacedR = false;
      for (int i = 0; i < c_str.length; i++){
         if (!hasReplacedR && i > replacementPosition && Character.isDigit(c_str[i])){
            newStr.append(Integer.valueOf(String.valueOf(c_str[i])).intValue() + rValue);
            hasReplacedR = true;
         }else{
            newStr.append(c_str[i]);
         }
      }

      //Find the last digit on the left and increase it
      

      System.out.println(" ==> " + newStr.toString());
      return newStr.toString();
   }

   private boolean isBeginningOfCouple(char[] c_str, int i) {
      if (i > c_str.length-3) return false;
      return    c_str[i] == '['
             && Character.isDigit(c_str[i+1]) 
             && c_str[i+2] == ',' 
             && Character.isDigit(c_str[i+3]) 
             && c_str[i+4] == ']';
   }
  
}
