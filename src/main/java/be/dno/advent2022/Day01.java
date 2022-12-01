package be.dno.advent2022;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.dno.Day;

public class Day01 extends Day {

   List<Long> allElves = new ArrayList<>();

   public Day01(){
		fileName = "2022/day01.txt";
	 }

   @Override
	public void fillDataStruct() {
      allElves.clear();
      Long runningTotal = 0l;
		for(String line : lines) {
         if (line.isEmpty()){
            allElves.add(runningTotal);
            runningTotal = 0l;
         }else{
            runningTotal += Long.valueOf(line);
         }
		}
      if (runningTotal > 0l){
         allElves.add(runningTotal);
      }
      Collections.sort(allElves);
	}

   private Long getTop(int number){
      long top = 0l;
      for (int i = allElves.size()-1; i >= allElves.size()-number; i--){
         top += allElves.get(i);
      }
      return top;
   }

   @Override
	public String processPart1() {
      return getTop(1)+"";
	}

   @Override
	public String processPart2() {
      return getTop(3)+"";
	}
}
