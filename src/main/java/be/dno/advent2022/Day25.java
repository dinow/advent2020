package be.dno.advent2022;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Map.Entry;

import be.dno.PointLong;

import be.dno.Day;
import be.dno.Point;
import be.dno.Point3d;
import be.dno.Utils;


public class Day25 extends Day {
   
   public Day25(){
      fileName = "2022/day25.txt";
   }

   @Override
   public void fillDataStruct() {
      
   }

   @Override
   public String processPart1() throws Exception {
      long totRes = 0l;
      for (String line : lines){
         long itemResult = 0l;
         String[] items = line.split("");
         for (int i = items.length-1; i >= 0; i--){
            int pow = items.length-(i+1);
            int curVal = -1;
            switch(items[i]){
               case "=":
                  curVal = -2;
                  break;
               case "-":
                  curVal = -1;
                  break;
               case "0":
                  curVal = 0;
                  break;
               case "1":
                  curVal = 1;
                  break;
               case "2":
                  curVal = 2;
                  break;
            }
            long actualPow = 1l;
            for (int j = 0; j < pow; j++){
               actualPow *= 5l;
            }
            long result = curVal * actualPow;
            itemResult += result;
         }
         System.out.println(line + " -> " + itemResult);
         totRes += itemResult;
      }
      //not 35798042807410
      //we need to convert 35798042807410 to the = - thing
      return Long.toString(totRes);
   }

   @Override
   public String processPart2() throws Exception {
      return "";
   }
}