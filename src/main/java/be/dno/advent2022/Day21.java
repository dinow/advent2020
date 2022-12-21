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


public class Day21 extends Day {
   
   private Map<String, Monkey> elements;

   static class Monkey {
      public Long finalValue;
      public Long value1Value;
      public String  value1Name;
      public Long value2Value;
      public String  value2Name;
      public String  operation;
      public String  name;

      public Monkey(String name, String v1, String v2, String op, Long fv){
         this.value1Name = v1;
         this.value2Name = v2;
         this.operation = op;
         this.value1Value = null;
         this.value2Value = null;
         this.name = name;
         this.finalValue = fv;
      }

      @Override
      public int hashCode() {
         final int prime = 31;
         int result = 1;
         result = prime * result + ((name == null) ? 0 : name.hashCode());
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
         Monkey other = (Monkey) obj;
         if (name == null) {
            if (other.name != null)
               return false;
         } else if (!name.equals(other.name))
            return false;
         return true;
      }

      
      
   }

   public Day21(){
      fileName = "2022/day21.txt";
   }

   @Override
   public void fillDataStruct() {
      elements = new HashMap<>();
      for (String line : lines){
         String[] elems = line.replaceAll(":", "").split(" ");
         if (elems.length == 4){
            elements.put(elems[0], new Monkey(elems[0], elems[1], elems[3], elems[2], null));
         } else {
            elements.put(elems[0], new Monkey(elems[0], null, null, null, Long.valueOf(elems[1])));
         }
      }
   }

   @Override
   public String processPart1() throws Exception {
      for (Monkey m : elements.values()){
         if (m.finalValue != null) yell(m);
      }
      return String.valueOf(elements.get("root").finalValue);
   }

   public void yell(Monkey monkey){
      for (Monkey m : elements.values()){
         if (m.finalValue != null) continue;
         if (m.value1Name.equals(monkey.name)) m.value1Value = monkey.finalValue;
         if (m.value2Name.equals(monkey.name)) m.value2Value = monkey.finalValue;
         if (m.value1Value != null && m.value2Value != null){
            switch(m.operation){
               case "+":
                  m.finalValue = m.value1Value + m.value2Value;
                  break;
               case "-":
                  m.finalValue = m.value1Value - m.value2Value;
                  break;
               case "*":
                  m.finalValue = m.value1Value * m.value2Value;
                  break;
               case "/":
                  m.finalValue = m.value1Value / m.value2Value;
                  break;
            }
            yell(m);
         } 
      }
   }

   @Override
   public String processPart2() throws Exception {
      return "";
   }
}