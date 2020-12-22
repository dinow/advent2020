package be.dno;

import be.dno.advent2020.*;

public class Advent {
	public static void main(String[] args) throws Exception {
		Day day = new Day22();

		long startTime = System.nanoTime();
		day.fillDataStruct("30,42,25,7,29,1,16,50,11,40,4,41,3,12,8,20,32,38,31,2,44,28,33,18,10;36,13,46,15,27,45,5,19,39,24,14,9,17,22,37,47,43,21,6,35,23,48,34,26,49");
		String part1 = day.processPart1();
		day.fillDataStruct("30,42,25,7,29,1,16,50,11,40,4,41,3,12,8,20,32,38,31,2,44,28,33,18,10;36,13,46,15,27,45,5,19,39,24,14,9,17,22,37,47,43,21,6,35,23,48,34,26,49");
		String part2 = day.processPart2();
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;

		System.out.println("Part 1 : " + part1);
		System.out.println("Part 2 : " + part2);
		System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);

	}
}
