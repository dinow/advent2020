package adventofcode;

import be.dno.Day;
import be.dno.advent2020.*;

public class DayTest {

	public static void main(String[] args) throws Exception {
		Day day = new Day22();

		long startTime = System.nanoTime();
		day.fillDataStruct("9,2,6,3,1;5,8,4,7,10");
		String part1 = "";//day.processPart1();
		day.fillDataStruct("43,19;2,29,14");
		String part2 = day.processPart2();
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;

		System.out.println("Part 1 : " + part1);
		System.out.println("Part 2 : " + part2);
		System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);


	}

}
