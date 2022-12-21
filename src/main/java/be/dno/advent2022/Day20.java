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


public class Day20 extends Day {
   
   private List<Elem> elements;
   private Elem zero;
   static class Elem {
      public long value;
      public int oriPosition;
      public Elem(long value, int oriP){
         this.value = value;
         this.oriPosition = oriP;
      }
      
      @Override
      public String toString() {
         return "[" + value + "]";
      }

      @Override
      public int hashCode() {
         final int prime = 31;
         int result = 1;
         result = prime * result + (int) (value ^ (value >>> 32));
         result = prime * result + oriPosition;
         return result;
      }
      @Override
      public boolean equals(Object obj) {
         if (this == obj)
            return true;
         if (obj == null)
            return false;
         if (getClass() != obj.getClass())
            return false;
         Elem other = (Elem) obj;
         if (value != other.value)
            return false;
         if (oriPosition != other.oriPosition)
            return false;
         return true;
      }
      
   }

   public Day20(){
      fileName = "2022/day20.txt";
   }

   @Override
   public void fillDataStruct() {
      elements = new ArrayList<>(lines.size());
      for (int i = 0; i < lines.size(); i++){
         elements.add(new Elem(Long.valueOf(lines.get(i)), i));
         if (lines.get(i).equals("0")){
            zero = new Elem(Long.valueOf(lines.get(i)), i);
         }
      }
   }

   @Override
   public String processPart1() throws Exception {
      List<Elem> originalList = new ArrayList<>(elements);
      for (Elem ori : originalList){
         int oriPos = elements.indexOf(ori);
         elements.remove(oriPos);
         int newPosition = Math.floorMod(oriPos + ori.value, elements.size());
         elements.add(newPosition, ori);
      }

      int startIndex = elements.indexOf(zero);
      return Long.toString(  elements.get((startIndex + 1000) % elements.size()).value 
                           + elements.get((startIndex + 2000) % elements.size()).value 
                           + elements.get((startIndex + 3000) % elements.size()).value);
   }

   @Override
   public String processPart2() throws Exception {
      for (Elem e : elements){
         e.value *= 8_115_891_53l;
      }
      List<Elem> originalList = new ArrayList<>(elements);
      for (int i = 0; i < 10; i++){
         for (Elem ori : originalList){
            int oriPos = elements.indexOf(ori);
            elements.remove(oriPos);
            int newPosition = Math.floorMod(oriPos + ori.value, elements.size());
            elements.add(newPosition, ori);
         }
      }

      int startIndex = elements.indexOf(zero);
      return Long.toString(  elements.get((startIndex + 1000) % elements.size()).value 
                           + elements.get((startIndex + 2000) % elements.size()).value 
                           + elements.get((startIndex + 3000) % elements.size()).value);
   }
}

