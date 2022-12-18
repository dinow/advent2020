package be.dno.advent2022;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import be.dno.PointLong;

import be.dno.Day;
import be.dno.Point;
import be.dno.Utils;


public class Day17 extends Day {

   class Block{
      private List<PointLong> elements;
      private String rep;
      

      public Block(String representation) {
         this.elements = new ArrayList<>();
         this.rep = representation;
      }

      public void add(long x, long y){
         this.elements.add(new PointLong(x,y));
      }

      private long leftItem(){
         long value = Long.MAX_VALUE;
         for (PointLong p : elements){
            value = Math.min(value, p.getX());
         }
         return value;
      }

      private long rightItem(){
         long value = Long.MIN_VALUE;
         for (PointLong p : elements){
            value = Math.max(value, p.getX());
         }
         return value;
      }

      private long topElem(){
         long value = Long.MAX_VALUE;
         for (PointLong p : elements){
            value = Math.min(value, p.getY());
         }
         return value;
      }
      private long bottomElem(){
         long value = Long.MIN_VALUE;
         for (PointLong p : elements){
            value = Math.max(value, p.getY());
         }
         return value;
      }

      public long getMinYAt(int i) {
         long value = Long.MAX_VALUE;
         for (PointLong p : elements){
            if(p.getX() == i){
               value = Math.min(value, p.getY());
            }
         }
         return value;
      }
   
      public long getMaxYAt(int i) {
         long value = Long.MIN_VALUE;
         for (PointLong p : elements){
            if(p.getX() == i){
               value = Math.max(value, p.getY());
            }
         }
         return value;
      }

      public boolean applyInstruction(String instruction) {
         //System.out.println("Apply " + instruction + " to " + this.rep);
         if (instruction.equals("<")){
            //Try to move left
            if (this.leftItem()>0){
               //check if no collision with any point in resting set
               boolean canMove = true;
               for (PointLong p : elements){
                  if (restingPoints.contains(new PointLong(p.getX()-1, p.getY()))){
                     canMove = false;
                  }
               }
               if (canMove){
                  for (PointLong p : elements){
                     p.setX(p.getX()-1);
                  }
               } else {
                  //Against resting bloc
               }
            } else {
               //Against wall
            }
         } else {
            //Try to move right
            if (this.rightItem()<6){
               //check if no collision with any point in resting set
               boolean canMove = true;
               for (PointLong p : elements){
                  if (restingPoints.contains(new PointLong(p.getX()+1, p.getY()))){
                     canMove = false;
                  }
               }
               if (canMove){
                  for (PointLong p : elements){
                     p.setX(p.getX()+1);
                  }
               } else {
                  //Against resting bloc
               }
            } else {
               //Against wall
            }
         }
         //move down if not resting
         if (!this.isResting()){
            for (PointLong p : elements){
               p.setY(p.getY()+1);
            }
            return true;
         }
         return false;
      }
   
      public boolean isResting() {
         //check if any element has a resting point in restingSet
         for (PointLong p : elements){
            if (restingPoints.contains(new PointLong(p.getX(), p.getY()+1))){
               return true;
            }
         }
         return false;
      }

      public Block clone(long highestY) {
         long bottomValue = highestY-=4;
         if (this.rep.equals("-")){
            this.elements.get(0).setLocation(2, bottomValue);
            this.elements.get(1).setLocation(3, bottomValue);
            this.elements.get(2).setLocation(4, bottomValue);
            this.elements.get(3).setLocation(5, bottomValue);
         } else if (this.rep.equals("+")){
            this.elements.get(0).setLocation(3,bottomValue-2);
            this.elements.get(1).setLocation(2,bottomValue-1);
            this.elements.get(2).setLocation(3,bottomValue-1);
            this.elements.get(3).setLocation(4,bottomValue-1);
            this.elements.get(4).setLocation(3,bottomValue-0);
         } else if (this.rep.equals("╝")){
            this.elements.get(0).setLocation(4,bottomValue-2);
            this.elements.get(1).setLocation(4,bottomValue-1);
            this.elements.get(2).setLocation(2,bottomValue-0);
            this.elements.get(3).setLocation(3,bottomValue-0);
            this.elements.get(4).setLocation(4,bottomValue-0);
         } else if (this.rep.equals("|")){
            this.elements.get(0).setLocation(2, bottomValue-3);
            this.elements.get(1).setLocation(2, bottomValue-2);
            this.elements.get(2).setLocation(2, bottomValue-1);
            this.elements.get(3).setLocation(2, bottomValue-0);
         } else if (this.rep.equals("■")){
            this.elements.get(0).setLocation(2,bottomValue-1);
            this.elements.get(1).setLocation(3,bottomValue-1);
            this.elements.get(2).setLocation(2,bottomValue-0);
            this.elements.get(3).setLocation(3,bottomValue-0);
         }
         Block blk = new Block(this.rep);
         for (PointLong p : this.elements){
            blk.add(p.getX(), p.getY());
         }
         return blk;
      }
   }

   String[] instructions;
   List<Block> blocs;
   Set<PointLong> restingPoints;

   public Day17(){
      fileName = "2022/day17.txt";
   }

   @Override
   public void fillDataStruct() {
      blocs         = new ArrayList<>();
      restingPoints = new HashSet<>();
      instructions = lines.get(0).split("");
      Block blocMinus = new Block("-");
      blocMinus.add(0,0);
      blocMinus.add(1,0);
      blocMinus.add(2,0);
      blocMinus.add(3,0);
      blocs.add(blocMinus);

      Block blocPlus = new Block("+");
      blocPlus.add(1,0);
      blocPlus.add(0,1);
      blocPlus.add(1,1);
      blocPlus.add(2,1);
      blocPlus.add(1,2);
      blocs.add(blocPlus);

      Block blocRevL = new Block("╝");
      blocRevL.add(2,0);
      blocRevL.add(2,1);
      blocRevL.add(0,2);
      blocRevL.add(1,2);
      blocRevL.add(2,2);
      blocs.add(blocRevL);

      Block blocPipe = new Block("|");
      blocPipe.add(0,0);
      blocPipe.add(0,1);
      blocPipe.add(0,2);
      blocPipe.add(0,3);
      blocs.add(blocPipe);
   
      Block blocSquare = new Block("■");
      blocSquare.add(0,0);
      blocSquare.add(1,0);
      blocSquare.add(0,1);
      blocSquare.add(1,1);
      blocs.add(blocSquare);

   }

   @Override
   public String processPart1() throws Exception {
      long nbStoppedBlocs = 0l;
      int blocsIndex = 0;
      int instructionIndex = 0;
      for (int i = 0; i < 7; i++){
         restingPoints.add(new PointLong(i, 0));
      }
      long highestY = 0;
      while (nbStoppedBlocs < 2_022l){
         Block currentBloc = blocs.get(blocsIndex).clone(highestY);
         while(true){
            String instruction = instructions[instructionIndex];
            instructionIndex++;
            if (instructionIndex == instructions.length) instructionIndex = 0;
            if (!currentBloc.applyInstruction(instruction)){
               nbStoppedBlocs++;
               //System.out.println("Block " + currentBloc.rep + " is resting, counter is " + nbStoppedBlocs);
               for (PointLong p : currentBloc.elements){
                  restingPoints.add(new PointLong(p.getX(), p.getY()));
               }
               highestY = Math.min(highestY, currentBloc.topElem());
               blocsIndex++;
               if (blocsIndex == blocs.size()) blocsIndex = 0;
               break;
            }
         }
      }
      return String.valueOf(highestY*-1);
   }

   

   @Override
   public String processPart2() throws Exception {
      System.out.print( (499_999_999_950l/53));
      long nbStoppedBlocs = 0l;
      int blocsIndex = 0;
      int instructionIndex = 0;
      Map<String, Long> cycles = new HashMap<>();
      for (int i = 0; i < 7; i++){
         restingPoints.add(new PointLong(i, 0));
      }
      long highestY = 0l;
      while (nbStoppedBlocs < 1_000_000_000_000l){
         Block currentBloc = blocs.get(blocsIndex).clone(highestY);
         String key = "land=["+getLand(currentBloc)+"],instructionIndex=["+instructionIndex+"],bloc=["+currentBloc.rep+"]";
         if (cycles.containsKey(key)){
            System.out.println("Cycle is " + nbStoppedBlocs + " current height is " + (highestY*-1)+" - " + cycles.get(key));
            throw new Exception();
         }
         cycles.put(key, Long.valueOf(highestY));
         while(true){
            String instruction = instructions[instructionIndex];
            instructionIndex++;
            if (instructionIndex == instructions.length) instructionIndex = 0;
            if (!currentBloc.applyInstruction(instruction)){
               nbStoppedBlocs++;
               //System.out.println("Block " + currentBloc.rep + " is resting, counter is " + nbStoppedBlocs);
               for (PointLong p : currentBloc.elements){
                  restingPoints.add(new PointLong(p.getX(), p.getY()));
               }
               highestY = Math.min(highestY, currentBloc.topElem());
               blocsIndex++;
               if (blocsIndex == blocs.size()) blocsIndex = 0;
               break;
            }
         }
      }
      return String.valueOf(highestY*-1);
   }

   private String getLand(Block b) {
      long[] highests = new long[]{1l, 1l, 1l, 1l, 1l, 1l, 1l};
      for (PointLong p : restingPoints){
         highests[(int)p.getX()] = Math.min(highests[(int)p.getX()],p.getY()-b.bottomElem());
      }

      return highests[0]+","
            +highests[1]+","
            +highests[2]+","
            +highests[3]+","
            +highests[4]+","
            +highests[5]+","
            +highests[6];
   }

}

