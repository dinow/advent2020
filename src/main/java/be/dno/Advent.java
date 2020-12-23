package be.dno;

import be.dno.advent2020.*;

public class Advent {
	public static void main(String[] args) throws Exception {
		Day day = new Day23();

		long startTimePart1 = System.nanoTime();
		day.fillDataStruct("167248359");
		String part1 = day.processPart1();
		long endTimePart1 = System.nanoTime();

		long startTimePart2 = System.nanoTime();
		day.fillDataStruct("167248359");
		String part2 = day.processPart2();
		long endTimePart2 = System.nanoTime();

		System.out.println("Part 1 : " + part1 + " -- " + ((endTimePart1 - startTimePart1)/1000) + "us");
		System.out.println("Part 2 : " + part2 + " -- " + ((endTimePart2 - startTimePart2)/1000) + "us");

	}
}
