package be.dno.advent2016;

import java.util.HashSet;
import java.util.Set;

import be.dno.Day;

public class Day15 extends Day{

   private Set<int[]> disks = new HashSet<>();

   public Day15(){
      fileName = null;
   }

   public void fillDataStruct(){
      disks.add(new int[]{1, 13, 10});
      disks.add(new int[]{2, 17, 15});
      disks.add(new int[]{3, 19, 17});
      disks.add(new int[]{4, 7, 1});
      disks.add(new int[]{5, 5, 0});
      disks.add(new int[]{6, 3, 1});
   }

   @Override
   public String processPart1() {
      int time = 0;
      boolean allok = false;
      while(!allok){
         allok = true;
         for (int[] disk : disks){
            allok &= isWorking(disk[0], disk[1], disk[2], time);
         }
         time++;
      }
      time--;
      return ""+time;
   }

   private boolean isWorking(int discNumber, int discPositions, int diskStartPos, int timeToTest){
      int diskCurrentTime = timeToTest + discNumber;
      int disPosition = (diskStartPos + diskCurrentTime) % (discPositions);
      return disPosition == 0;
   }

   
   @Override
   public String processPart2() {
      int time = 0;
      boolean allok = false;
      disks.add(new int[]{7, 11, 0});
      while(!allok){
         allok = true;
         for (int[] disk : disks){
            allok &= isWorking(disk[0], disk[1], disk[2], time);
         }
         time++;
      }
      time--;
      return ""+time;
   }

}
