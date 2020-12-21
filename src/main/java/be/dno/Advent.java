package be.dno;

import be.dno.advent2020.Day21;

public class Advent {
	public static void main(String[] args) throws Exception {
		Day day = new Day21();

		long startTime = System.nanoTime();
		day.fillDataStruct("day21.txt");
		long part1 = day.processPart1();
		if (part1 != 5) throw new Exception("Wrong result: " + part1);
		long part2 = day.processPart2();
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;

		System.out.println("Part 1 : " + part1);
		System.out.println("Part 2 : " + part2);
		System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);

	}
}
