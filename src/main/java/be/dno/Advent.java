package be.dno;

import be.dno.advent2020.*;

public class Advent {
	public static void main(String[] args) throws Exception {
		Day day = new Day23();
		day.readLines("2020/day23.txt");
		long startTimeDS = System.nanoTime();
		day.fillDataStruct();
		long endTimeDS = System.nanoTime();
		long startTimePart1 = System.nanoTime();
		String part1 = day.processPart1();
		long endTimePart1 = System.nanoTime();
		//day.fillDataStruct("2020/day24.txt");
		long startTimePart2 = System.nanoTime();
		String part2 = day.processPart2();
		long endTimePart2 = System.nanoTime();
		System.out.println("Data Structure -- " + ((endTimeDS - startTimeDS)/100000) + "ms");
		System.out.println("Part 1 : " + part1 + " -- " + ((endTimePart1 - startTimePart1)/100000) + "ms");
		System.out.println("Part 2 : " + part2 + " -- " + ((endTimePart2 - startTimePart2)/100000) + "ms");

	}
}
