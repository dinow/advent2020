package be.dno;


import be.dno.advent2022.*;

public class Advent {
   public static void main(String[] args) throws Exception {
      Day day = new Day05();
      day.readLines();
      long startTimeDS = System.nanoTime();
      day.fillDataStruct();
      long endTimeDS = System.nanoTime();
      System.out.println("Data Structure -- " + ((endTimeDS - startTimeDS)/100000) + "ms");
      long startTimePart1 = System.nanoTime();
      String part1 = day.processPart1();
      long endTimePart1 = System.nanoTime();
      System.out.println("Part 1 : " + part1 + " -- " + ((endTimePart1 - startTimePart1)/100000) + "ms");
      day.fillDataStruct();
      long startTimePart2 = System.nanoTime();
      String part2 = day.processPart2();
      long endTimePart2 = System.nanoTime();
      System.out.println("Part 2 : " + part2 + " -- " + ((endTimePart2 - startTimePart2)/100000) + "ms");
   }
}
